package my.virkato.kata311.controller;

import my.virkato.kata311.model.User;
import my.virkato.kata311.repo.UserRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final CrudRepository<User, Long> userRepo;

    public UsersController(UserRepo userRepo) {
        this.userRepo = userRepo;
        addUsers();
    }

    //--- CREATE

    /***
     * Подготовить объект User для сохранения в базу
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "users/new";
    }

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        // параметры собираются сразу в модель User
        userRepo.save(user);
        return "redirect:users";
    }

    //--- READ

    /***
     * Получить всех пользователей
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users/all";
    }

    /***
     * Получить одного пользователя
     */
    @GetMapping("/{id}")
    public String read(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userRepo.findById(id).orElse(new User()));
        return "users/show";
    }

    //--- UPDATE

    /***
     * Подготовить изменения для объекта User
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userRepo.findById(id).orElse(new User()));
        return "users/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        user.setId(id);
        userRepo.save(user);
        return "redirect:/users";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }

    //--- TECHNICAL

    /***
     * Подготовим список пользователей в базе
     */
    private void addUsers() {
        userRepo.save(new User("Степан", "Иванов"));
        userRepo.save(new User("Иван", "Петров"));
        userRepo.save(new User("Света", "Вихрь"));
        userRepo.save(new User("Алина", "Хац"));
    }

}
