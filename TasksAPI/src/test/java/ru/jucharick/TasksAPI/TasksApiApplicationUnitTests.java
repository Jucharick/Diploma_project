package ru.jucharick.TasksAPI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.TaskStatus;
import ru.jucharick.TasksAPI.domain.Team;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.TaskNotFoundException;
import ru.jucharick.TasksAPI.exception.UserNotFoundException;
import ru.jucharick.TasksAPI.repositories.TaskRepository;
import ru.jucharick.TasksAPI.repositories.UserRepository;
import ru.jucharick.TasksAPI.services.impl.iTaskService;
import ru.jucharick.TasksAPI.services.impl.iUserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TasksApiApplicationUnitTests {
	@Mock
	private TaskRepository taskRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private iTaskService taskService;

	@InjectMocks
	private iUserService userService;

	// tasks
	@Test
	public void getTaskByIdHappyFlow(){
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		TaskStatus taskStatus = TaskStatus.in_progress;
		User user = new User();
		Task task = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		Long expectedId = task.getTask_id();

		when(taskRepository.findById(expectedId)).thenReturn(Optional.of(task));

		// action
		Task expectedTask = taskService.getTaskById(expectedId);

		// check
		assertEquals(expectedTask.getTitle(), task.getTitle());
		assertEquals(expectedTask.getCreateDate(), task.getCreateDate());
		assertEquals(expectedTask.getUpdateDate(), task.getUpdateDate());
		assertEquals(expectedTask.getDeadline(), task.getDeadline());
		assertEquals(expectedTask.getDescription(), task.getDescription());
		assertEquals(expectedTask.getStatus(), task.getStatus());
		assertEquals(expectedTask.getRequestedBy(), task.getRequestedBy());
		assertEquals(expectedTask.getAssignedBy(), task.getAssignedBy());
		assertEquals(expectedTask.getAssigneeID(), task.getAssigneeID());

		verify(taskRepository).findById(expectedId);
	}

	@Test
	public void getAllTasksHappyFlow(){
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		List<Task> dataTasks = new ArrayList<>();
		TaskStatus taskStatus = TaskStatus.in_progress;
		User user = new User();
		Task task1 = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		Task task2 = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		Task task3 = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		dataTasks.add(task1);
		dataTasks.add(task2);
		dataTasks.add(task3);

		when(taskRepository.findAll()).thenReturn(dataTasks);

		// action
		List<Task> expectedTasks = taskService.findAll();

		// check
		assertEquals(expectedTasks, dataTasks);

		verify(taskRepository).findAll();
	}

	@Test
	public void getTaskByIdNotFoundFlow() {
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		TaskStatus taskStatus = TaskStatus.in_progress;
		User user = new User();
		Task task = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		Long expectedId = task.getTask_id();

		given(taskRepository.findById(expectedId))
				.willReturn(Optional.empty());

		// action
		assertThrows(TaskNotFoundException.class,
				() -> taskService.getTaskById(expectedId)
		);

		// check
		verify(taskRepository).findById(expectedId);
	}

	@Test
	public void createTaskHappyFlow() {
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		TaskStatus taskStatus = TaskStatus.in_progress;
		User user = new User();
		Task task = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);

		when(taskRepository.save(task)).thenReturn(task);

		// action
		Task expectedTask = taskService.createTask(task);

		// check
		assertEquals(expectedTask,task);

		verify(taskRepository).save(task);
	}

	@Test
	public void updateTaskHappyFlow() {
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		TaskStatus taskStatus = TaskStatus.in_progress;
		User user = new User();
		Task taskTest = new Task(1L,"Test",dateTime,dateTime,dateTime,"TestTestTest",taskStatus,user,user,user);
		Long expectedId = taskTest.getTask_id();

		Task taskById = new Task(1L,"OldTest",dateTime,dateTime,dateTime,"OldTestTestTest",taskStatus,user,user,user);

		when(taskRepository.findById(expectedId)).thenReturn(Optional.of(taskById));
		when(taskRepository.save(taskById)).thenReturn(taskById);

		// action
		Task expectedTask = taskService.updateTask(expectedId, taskTest);

		// check
		assertEquals(expectedTask.getTitle(), taskTest.getTitle());
		assertEquals(expectedTask.getCreateDate(), taskTest.getCreateDate());
		assertEquals(expectedTask.getDeadline(), taskTest.getDeadline());
		assertEquals(expectedTask.getDescription(), taskTest.getDescription());
		assertEquals(expectedTask.getStatus(), taskTest.getStatus());
		assertEquals(expectedTask.getRequestedBy(), taskTest.getRequestedBy());
		assertEquals(expectedTask.getAssignedBy(), taskTest.getAssignedBy());
		assertEquals(expectedTask.getAssigneeID(), taskTest.getAssigneeID());

		verify(taskRepository).findById(expectedId);
		verify(taskRepository).save(taskById);
	}

	@Test
	public void deleteTaskHappyFlow() {
		// pre
		LocalDateTime dateTime = LocalDateTime.now();
		TaskStatus taskStatus = TaskStatus.in_progress;
		Task taskById = new Task(1L, "Test", dateTime, dateTime, dateTime, "TestTestTest", taskStatus,null,null,null);
		Long expectedId = taskById.getTask_id();

		when(taskRepository.findById(expectedId)).thenReturn(Optional.of(taskById));

		// action
		taskService.deleteById(expectedId);

		// check
		verify(taskRepository).findById(expectedId);
		verify(taskRepository).delete(taskById);
	}

	// users
	@Test
	public void getUserByIdHappyFlow(){
		// pre
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User user = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		Long expectedId = user.getId();

		when(userRepository.findById(expectedId)).thenReturn(Optional.of(user));

		// action
		User expectedUser = userService.getUserById(expectedId);

		// check
		assertEquals(expectedUser.getFirstName(), user.getFirstName());
		assertEquals(expectedUser.getLastName(), user.getLastName());
		assertEquals(expectedUser.getPatronymic(), user.getPatronymic());
		assertEquals(expectedUser.getBirthday(), user.getBirthday());
		assertEquals(expectedUser.getPosition(), user.getPosition());
		assertEquals(expectedUser.getTeam(), user.getTeam());
		assertEquals(expectedUser.getPhone(), user.getPhone());
		assertEquals(expectedUser.getEmail(), user.getEmail());

		verify(userRepository).findById(expectedId);
	}

	@Test
	public void getAllUsersHappyFlow(){
		// pre
		List<User> dataUsers = new ArrayList<>();
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User user1 = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		User user2 = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		User user3 = new User();
		dataUsers.add(user1);
		dataUsers.add(user2);
		dataUsers.add(user2);

		when(userRepository.findAll()).thenReturn(dataUsers);

		// action
		List<User> expectedUsers = userService.findAll();

		// check
		assertEquals(expectedUsers, dataUsers);

		verify(userRepository).findAll();
	}

	@Test
	public void getUserByIdNotFoundFlow() {
		// pre
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User user = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		Long expectedId = user.getId();

		given(userRepository.findById(expectedId))
				.willReturn(Optional.empty());

		// action
		assertThrows(UserNotFoundException.class,
				() -> userService.getUserById(expectedId)
		);

		// check
		verify(userRepository).findById(expectedId);
	}

	@Test
	public void createUserHappyFlow() {
		// pre
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User user = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		Long expectedId = user.getId();

		when(userRepository.save(user)).thenReturn(user);

		// action
		User expectedUser = userService.createUser(user);

		// check
		assertEquals(expectedUser,user);

		verify(userRepository).save(user);
	}

	@Test
	public void updateUserHappyFlow() {
		// pre
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User userTest = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		Long expectedId = userTest.getId();

		User userById = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());

		when(userRepository.findById(expectedId)).thenReturn(Optional.of(userById));
		when(userRepository.save(userById)).thenReturn(userById);

		// action
		User expectedUser = userService.updateUser(expectedId, userById);

		// check
		assertEquals(expectedUser.getFirstName(), userTest.getFirstName());
		assertEquals(expectedUser.getLastName(), userTest.getLastName());
		assertEquals(expectedUser.getPatronymic(), userTest.getPatronymic());
		assertEquals(expectedUser.getBirthday(), userTest.getBirthday());
		assertEquals(expectedUser.getPosition(), userTest.getPosition());
		assertEquals(expectedUser.getTeam(), userTest.getTeam());
		assertEquals(expectedUser.getPhone(), userTest.getPhone());
		assertEquals(expectedUser.getEmail(), userTest.getEmail());


		verify(userRepository).findById(expectedId);
		verify(userRepository).save(userById);
	}


	@Test
	public void deleteUserHappyFlow() {
		// pre
		LocalDate date = LocalDate.now();
		Team team = new Team();
		User userById = new User(1L,"first_name","last_name","patronymic",date,"position",team,new ArrayList<>(),new ArrayList<>());
		Long expectedId = userById.getId();

		when(userRepository.findById(expectedId)).thenReturn(Optional.of(userById));

		// action
		userService.deleteById(expectedId);

		// check
		verify(userRepository).findById(expectedId);
		verify(userRepository).delete(userById);
	}
}
