package ru.jucharick.TasksAPI.services.servicesReports;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.repositories.TaskRepository;
import ru.jucharick.TasksAPI.repositories.TeamRepository;
import ru.jucharick.TasksAPI.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    //region
    /**
     * TaskRepository
     */
    private final TaskRepository taskRepository;
    /**
     * UserRepository
     */
    private final UserRepository userRepository;
    /**
     * TeamRepository
     */
    private final TeamRepository teamRepository;
    //endregion

    public void generateExcelTasks(HttpServletResponse response) throws Exception {

        List<Task> tasks = taskRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Tasks Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("task_id");
        row.createCell(1).setCellValue("title");
        row.createCell(2).setCellValue("create date");
        row.createCell(3).setCellValue("update date");
        row.createCell(4).setCellValue("deadline");
        row.createCell(5).setCellValue("description");
        row.createCell(6).setCellValue("status");
        row.createCell(7).setCellValue("requestedBy_id");
        row.createCell(8).setCellValue("requestedBy_first_name");
        row.createCell(9).setCellValue("requestedBy_last_name");
        row.createCell(10).setCellValue("requestedBy_position");
        row.createCell(11).setCellValue("requestedBy_team");
        row.createCell(12).setCellValue("assignedBy_id");
        row.createCell(13).setCellValue("assignedBy_first_name");
        row.createCell(14).setCellValue("assignedBy_last_name");
        row.createCell(15).setCellValue("assignedBy_position");
        row.createCell(16).setCellValue("assignedBy_team");
        row.createCell(17).setCellValue("executor_id");
        row.createCell(18).setCellValue("executor_first_name");
        row.createCell(19).setCellValue("executor_last_name");
        row.createCell(20).setCellValue("executor_position");
        row.createCell(21).setCellValue("executor_team");

        int dataRowIndex = 1;

        for (Task task : tasks) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(task.getTask_id());
            dataRow.createCell(1).setCellValue(task.getTitle());
            dataRow.createCell(2).setCellValue(task.getCreateDate().toString());
            dataRow.createCell(3).setCellValue(task.getUpdateDate().toString());
            dataRow.createCell(4).setCellValue(task.getDeadline().toString());
            dataRow.createCell(5).setCellValue(task.getDescription());
            dataRow.createCell(6).setCellValue(task.getStatus().toString());
            dataRow.createCell(7).setCellValue(task.getRequestedBy().getId());
            dataRow.createCell(8).setCellValue(task.getRequestedBy().getFirstName());
            dataRow.createCell(9).setCellValue(task.getRequestedBy().getLastName());
            dataRow.createCell(10).setCellValue(task.getRequestedBy().getPosition());
            dataRow.createCell(11).setCellValue(task.getRequestedBy().getTeam().getTeamTitle());
            dataRow.createCell(12).setCellValue(task.getAssignedBy().getId());
            dataRow.createCell(13).setCellValue(task.getAssignedBy().getFirstName());
            dataRow.createCell(14).setCellValue(task.getAssignedBy().getLastName());
            dataRow.createCell(15).setCellValue(task.getAssignedBy().getPosition());
            dataRow.createCell(16).setCellValue(task.getAssignedBy().getTeam().getTeamTitle());
            dataRow.createCell(17).setCellValue(task.getAssigneeID().getId());
            dataRow.createCell(18).setCellValue(task.getAssigneeID().getFirstName());
            dataRow.createCell(19).setCellValue(task.getAssigneeID().getLastName());
            dataRow.createCell(20).setCellValue(task.getAssigneeID().getPosition());
            dataRow.createCell(21).setCellValue(task.getAssigneeID().getTeam().getTeamTitle());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    public void generateExcelTasksByUser(HttpServletResponse response,User user) throws Exception {

        List<Task> tasks = taskRepository.findByAssigneeID(user);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(user.getId() + "_" + user.getLastName() + "_tasks");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("task_id");
        row.createCell(1).setCellValue("title");
        row.createCell(2).setCellValue("create date");
        row.createCell(3).setCellValue("update date");
        row.createCell(4).setCellValue("deadline");
        row.createCell(5).setCellValue("description");
        row.createCell(6).setCellValue("status");
        row.createCell(7).setCellValue("requestedBy_id");
        row.createCell(8).setCellValue("requestedBy_first_name");
        row.createCell(9).setCellValue("requestedBy_last_name");
        row.createCell(10).setCellValue("requestedBy_position");
        row.createCell(11).setCellValue("requestedBy_team");
        row.createCell(12).setCellValue("assignedBy_id");
        row.createCell(13).setCellValue("assignedBy_first_name");
        row.createCell(14).setCellValue("assignedBy_last_name");
        row.createCell(15).setCellValue("assignedBy_position");
        row.createCell(16).setCellValue("assignedBy_team");
        row.createCell(17).setCellValue("executor_id");
        row.createCell(18).setCellValue("executor_first_name");
        row.createCell(19).setCellValue("executor_last_name");
        row.createCell(20).setCellValue("executor_position");
        row.createCell(21).setCellValue("executor_team");

        int dataRowIndex = 1;

        for (Task task : tasks) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(task.getTask_id());
            dataRow.createCell(1).setCellValue(task.getTitle());
            dataRow.createCell(2).setCellValue(task.getCreateDate().toString());
            dataRow.createCell(3).setCellValue(task.getUpdateDate().toString());
            dataRow.createCell(4).setCellValue(task.getDeadline().toString());
            dataRow.createCell(5).setCellValue(task.getDescription());
            dataRow.createCell(6).setCellValue(task.getStatus().toString());
            dataRow.createCell(7).setCellValue(task.getRequestedBy().getId());
            dataRow.createCell(8).setCellValue(task.getRequestedBy().getFirstName());
            dataRow.createCell(9).setCellValue(task.getRequestedBy().getLastName());
            dataRow.createCell(10).setCellValue(task.getRequestedBy().getPosition());
            dataRow.createCell(11).setCellValue(task.getRequestedBy().getTeam().getTeamTitle());
            dataRow.createCell(12).setCellValue(task.getAssignedBy().getId());
            dataRow.createCell(13).setCellValue(task.getAssignedBy().getFirstName());
            dataRow.createCell(14).setCellValue(task.getAssignedBy().getLastName());
            dataRow.createCell(15).setCellValue(task.getAssignedBy().getPosition());
            dataRow.createCell(16).setCellValue(task.getAssignedBy().getTeam().getTeamTitle());
            dataRow.createCell(17).setCellValue(task.getAssigneeID().getId());
            dataRow.createCell(18).setCellValue(task.getAssigneeID().getFirstName());
            dataRow.createCell(19).setCellValue(task.getAssigneeID().getLastName());
            dataRow.createCell(20).setCellValue(task.getAssigneeID().getPosition());
            dataRow.createCell(21).setCellValue(task.getAssigneeID().getTeam().getTeamTitle());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    public void generateExcelUsers(HttpServletResponse response) throws Exception {
        List<User> users = userRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Tasks Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("user_id");
        row.createCell(1).setCellValue("first_name");
        row.createCell(2).setCellValue("last_name");
        row.createCell(3).setCellValue("patronymic");
        row.createCell(4).setCellValue("birthday");
        row.createCell(5).setCellValue("position");
        row.createCell(6).setCellValue("team");
        row.createCell(7).setCellValue("email");
        row.createCell(8).setCellValue("phone");

        int dataRowIndex = 1;

        for (User user : users) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(user.getId());
            dataRow.createCell(1).setCellValue(user.getFirstName());
            dataRow.createCell(2).setCellValue(user.getLastName());
            dataRow.createCell(3).setCellValue(user.getPatronymic());
            dataRow.createCell(4).setCellValue(user.getBirthday().toString());
            dataRow.createCell(5).setCellValue(user.getPosition());
            dataRow.createCell(6).setCellValue(user.getTeam().getTeamTitle());
            dataRow.createCell(7).setCellValue(user.getEmail().toString());
            dataRow.createCell(8).setCellValue(user.getPhone().toString());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }


}
