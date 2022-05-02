package com.projectManagement.service.impl;

import static com.projectManagement.consts.Common.MSG_ERR_EDITING_NOT_CURRENT_USER_IS_FORBIDDEN;
import static com.projectManagement.consts.Common.MSG_ERR_GETTING_NOT_CURRENT_USER_IS_FORBIDDEN;
import static com.projectManagement.consts.Common.MSG_ERR_USER_ALREADY_EXISTS;
import static com.projectManagement.consts.Common.MSG_ERR_USER_WASN_T_FOUND;

import com.projectManagement.dto.UserDto;
import com.projectManagement.entity.UserEntity;
import com.projectManagement.entity.UserRole;
import com.projectManagement.entity.UserStatus;
import com.projectManagement.repository.UserRepository;
import com.projectManagement.service.UserService;
import com.projectManagement.service.annotations.SelfInject;
import com.projectManagement.service.exceptions.NotCurrentUserException;
import com.projectManagement.service.exceptions.NotEnoughPoundsException;
import com.projectManagement.service.exceptions.UserAlreadyExistsException;
import com.projectManagement.service.exceptions.UserNotFoundException;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private static final float INITIAL_MONEY_VALUE = 0f;
    private static final Long ADMIN_ID = 1L;
    private static final String DRIVER = "DRIVER";
    private static final String NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT = "Not enough pounds on admin account!";

    @Autowired
    UserRepository userRepository;

    @SelfInject
    public UserService userServiceProxy;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    //TODO: FIX IT
    @Override
    public List<UserDto> findAllDrivers() {
        final String driver = UserRole.USER.getName();
        return userRepository.findAllByRole(driver).stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getDriverById(Long userId) {
        return DtoMapper.toUserDto(userRepository.findAllByIdAndStatus(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    public UserDto getMe(Long userId, String authenticationName) {

        Optional<UserEntity> userByIdAndRoleEntityName = userRepository.findAllByIdAndStatus(userId, DRIVER);

        if (!userByIdAndRoleEntityName.orElseThrow(() -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)).getLogin().equals(authenticationName)) {
            throw new NotCurrentUserException(MSG_ERR_GETTING_NOT_CURRENT_USER_IS_FORBIDDEN);
        }

        return DtoMapper.toUserDto(userByIdAndRoleEntityName.get());
    }

    @Override
    public UserDto getAdmin() {
        final String admin = UserRole.ADMIN.getName();
        return DtoMapper.toUserDto(userRepository.findAllByStatus(admin).stream().findAny().get());
    }

    // FIXME: Try to change if-clause to lambda-style using ifPresent. I've noticed that it demands Java 9+ version of compiler
    @Override
    public Optional<UserDto> findByLogin(String login) {
        Optional<UserEntity> byLogin = userRepository.findByLogin(login);
        Optional<UserEntity> userEntity = byLogin.stream().findAny();
        if (!userEntity.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(DtoMapper.toUserDto(byLogin.stream().findAny().get()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto editUser(UserDto userDto) {
        userRepository.updateUser(userDto.getUserID(), userDto.getLastName(),
            userDto.getFirstName(), userDto.getPatronymic(),userDto.getBirthday(), userDto.getEmailAddress(),
            userDto.getPassword(), userDto.getMoney());

        return DtoMapper.toUserDto(userRepository.findAllByIdAndStatus(userDto.getUserID(), DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
     }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto editMe(UserDto userDto, String authenticationName) {

        if (!userDto.getEmailAddress().equals(authenticationName)) {
            throw new NotCurrentUserException(MSG_ERR_EDITING_NOT_CURRENT_USER_IS_FORBIDDEN);
        }

        userRepository.updateUser(userDto.getUserID(), userDto.getLastName(),
            userDto.getFirstName(), userDto.getPatronymic(),userDto.getBirthday(), userDto.getEmailAddress(),
            userDto.getPassword(), userDto.getMoney());

        return DtoMapper.toUserDto(userRepository.findAllByIdAndStatus(userDto.getUserID(), DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto changeUserStatus(Long userId, String userStatus) {

        userRepository.updateUserStatus(userId, userStatus);

        return DtoMapper.toUserDto(userRepository.findAllByIdAndStatus(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<UserDto> deleteUser(Long userId) {

        userRepository.findAllByIdAndStatus(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        );

        userRepository.deleteById(userId);

        return userRepository.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createNewUser(UserDto userDto) {

        //TODO - Try to check working of correct transaction work
        Optional<UserDto> userByLogin = this.findByLogin(userDto.getEmailAddress());
        userByLogin.ifPresent(ob -> {throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);});

        UserEntity userEntity = userRepository.findAllByIdAndRole(userDto.getUserID(), UserRole.USER).orElse(
            new UserEntity(userDto.getLastName(), userDto.getFirstName(), userDto.getPatronymic(), userDto.getBirthday(),
                userDto.getEmailAddress(), userDto.getPassword(), INITIAL_MONEY_VALUE, UserRole.USER, UserStatus.FREE)
        );

        userRepository.save(userEntity);
    }

    /**
     * This is the case from Eugene Borisov report with self-inject problem in Spring.
     * By default Spring makes rollbackFor RunTimeException. Therefore we have to consider it.
     * Using noRollbackFor = {RuntimeException.class} I just want to say that I don't need default behaviour in transaction.
     * The helpful article about @Transaction - https://akorsa.ru/2017/01/sovety-i-oshibki-v-spring-transactions/
     * @param userId
     * @param amount
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {RuntimeException.class})
    public void transferMoney(Long userId, Float amount) throws Exception {
        userRepository.updateUserStatus(userId, UserStatus.FREE.name());
        userServiceProxy.withdraw(ADMIN_ID, amount);
        userServiceProxy.deposit(userId, amount);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void withdraw(Long fromUser, Float amount) throws Exception {
        Float adminInitialAmount = getAdmin().getMoney();
        Float resultAdminAmount = adminInitialAmount - amount;
        if (amount < adminInitialAmount ) {
            userRepository.updateBalance(fromUser, resultAdminAmount);
        } else {
            throw new NotEnoughPoundsException(NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deposit(Long toUser, Float amount) throws Exception {
        Float userInitialAmount = getDriverById(toUser).getMoney();
        Float resultUserAmount = userInitialAmount + amount;
        userRepository.updateBalance(toUser, resultUserAmount);
        userServiceProxy.informBankManager();
    }

    /**
     *     In case when noRollbackFor = {Exception.class, IllegalStateException.class} and we have two queries: userRepository.updateUser,
     *     carRepository.updateCar here and when second one will throw an exception (existedBrandId - doesn't exist), for some reason Spring will throw:
     *     org.springframework.transaction.UnexpectedRollbackException - "Transaction silently rolled back because it has been marked as rollback-only"
     *     this transaction for some reason will be rolled back.
     *     I found one answer, but I don't sure that it's the right reason:
     *     https://stackoverflow.com/questions/19349898/unexpectedrollbackexception-transaction-rolled-back-because-it-has-been-marked
     *     Great article about transactional - https://akorsa.ru/2017/01/sovety-i-oshibki-v-spring-transactions/
     *     Update from 18.08.2020 - По умолчанию транзакцкия откатывается при возникновении ошибки, но параметором rollbackFor
     *         можно уточнить, при возникновении какой именно откатывать транзакцию.
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void informBankManager() throws Exception {
        System.out.println("Send email to the manager.");
/*        final int existedCarId = 8;
        final int existedBrandId = 6;
        final int unExistedBrandId = 111;
        final String newNumber = "HT-8338";
        final int existedUserId = 9;
        final String newLastName = "Vasilev9";
        //userRepository.updateLastName(existedUserId, newLastName);
        carRepository.updateCar(existedCarId, existedBrandId, new Date(), newNumber, new Date(), CarStatus.FREE.getId());*/
        //final int i = 1/0;
        //throw new Exception();
        //throw new RuntimeException();
    }

}
