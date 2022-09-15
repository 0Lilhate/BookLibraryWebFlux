package projectJDBC.shell;

import org.springframework.stereotype.Repository;
import projectJDBC.service.GetDateService;

//@ShellComponent
//@Repository
//public class ShellCommand {
//
//    private final GetDateService getDateService;
//
//    public ShellCommand(GetDateService getDateService) {
//        this.getDateService = getDateService;
//    }
//
//    @ShellMethod(value = "Insert book", key = {"i", "insert"})
//    public String insertBook(){
//        getDateService.insertBook();
//        return "Мы добавили в бд";
//    }
//
//    @ShellMethod(value = "Get All book", key = {"getAll", "getAllBook"})
//    public String getAllBook(){
//        getDateService.getAllBook();
//        return "Метод закончил выполнение";
//    }
//
//
//}
