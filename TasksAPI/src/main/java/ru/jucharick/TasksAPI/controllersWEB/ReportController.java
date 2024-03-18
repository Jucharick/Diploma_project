package ru.jucharick.TasksAPI.controllersWEB;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.services.UserServiceApi;
import ru.jucharick.TasksAPI.servicesReports.ReportService;

@Controller
@AllArgsConstructor
public class ReportController {
    //region Поля
    /**
     * UserService
     */
    private final UserServiceApi userService;
    /**
     * ReportService
     */
    private final ReportService reportService;
    //endregion

    //region Методы
    /**
     * Выгрузка всех задач в excel.
     */
    @GetMapping("/tasks/report/excel")
    public void generateExcelReportTasks(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=tasks.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateExcelTasks(response);
        response.flushBuffer();
    }

    /**
     * Выгрузка всех пользователей в excel.
     */
    @GetMapping("/users/report/excel")
    public void generateExcelReportUsers(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=users.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateExcelUsers(response);
        response.flushBuffer();
    }

    /**
     * Выгрузка задач в разрезе пользователя в excel.
     */
    @GetMapping("/user/{id}/report/excel")
    public void generateExcelReportTasksByUser(@PathVariable("id") Long id, HttpServletResponse response) throws Exception{
        User user = userService.getUserById(id);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=" + user.getId() + "_" + user.getLastName() + "_tasks.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateExcelTasksByUser(response,user);
        response.flushBuffer();
    }
    //endregion
}
