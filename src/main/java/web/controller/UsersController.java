//package web.controller;
//
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import web.dao.UserDaoImpl;
//import web.models.User;
//
//@Controller
//@RequestMapping("/users")
//public class UsersController {
//
//    private final UserDaoImpl userDao;
//
//    public UsersController(UserDaoImpl userDao) {
//        this.userDao = userDao;
//
//    }
//
//    @GetMapping()
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userDao.getAllUsers());
//        return "index"; // Имя представления для отображения всех пользователей
//    }
//
//    @GetMapping("/view") // Используем /view для получения пользователя по ID
//    public String getById(@RequestParam("id") int id, Model model) {
//        model.addAttribute("user", userDao.getById(id));
//        return "show"; // Имя представления для показа пользователя
//    }
//
//    @GetMapping("/new") // Путь для нового пользователя
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new"; // Имя представления для создания нового пользователя
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "new"; // Если есть ошибки, возвращаем на страницу создания
//        }
//        userDao.save(user); // Сохраняем нового пользователя
//        return "redirect:/users"; // Перенаправление на список пользователей
//    }
//
//    @GetMapping("/edit") // Обновлено для соответствия GET-запросу
//    public String edit(@RequestParam("id") int id, Model model) {
//        model.addAttribute("user", userDao.getById(id));
//        return "edit"; // Имя представления для редактирования пользователя
//    }
//
//    @PostMapping("/update") // Обновлено для использования POST
//    public String update(@RequestParam("id") int id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "edit"; // Если есть ошибки, возвращаем на страницу редактирования
//        userDao.update(id, user); // Обновляем пользователя
//        return "redirect:/users"; // Перенаправление на список пользователей
//    }
//
//    @PostMapping("/delete") // Удаление пользователя
//    public String delete(@RequestParam("id") int id) {
//        userDao.deleteUserById(id); // Удаляем пользователя по ID
//        return "redirect:/users"; // Перенаправление на список пользователей
//    }
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e, Model model) {
//        model.addAttribute("errorMessage", e.getMessage());
//        return "error"; // Путь к вашему представлению для ошибок
//    }
//}


package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "new";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable int id){
        model.addAttribute("user", userService.getById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, @PathVariable int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "update";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }
}
