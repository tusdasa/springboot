package net.tusdasa.demo.controller;

import net.tusdasa.demo.entity.User;
import net.tusdasa.demo.utils.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

@CrossOrigin
@Controller
public class Index {

    private DataRepository dataRepository = DataRepository.getInstance();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexView(Model model){
        LinkedList<User> userArrayList = dataRepository.getUserArrayList();
        model.addAttribute("users", userArrayList);
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createView(){
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@RequestParam(value = "firstName", defaultValue = "") String firstName,
                             @RequestParam(value = "lastName", defaultValue = "") String lastName,
                             @RequestParam(value = "birthday", defaultValue = "Fri Feb 26 13:11:26 CST 2021") String birthday) throws ParseException {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !birthday.isEmpty()){
            LinkedList<User> userArrayList = dataRepository.getUserArrayList();
            Integer index = dataRepository.getIndex();
            // index++ 0 0
            // ++index 1
            userArrayList.addLast(new User(++index, firstName, lastName, new SimpleDateFormat("yyyy-MM-dd").parse(birthday)));
            dataRepository.setIndex(index);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{uid}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable(value = "uid",required = true) Integer uid){
        LinkedList<User> userArrayList = dataRepository.getUserArrayList();
        if (!userArrayList.isEmpty()){
            for (User user :userArrayList) {
                if (user.getUid().equals(uid)){
                    userArrayList.remove(user);
                    break;
                }
            }
        }
        return "ok";
    }

    @RequestMapping(value = "/update/{uid}", method = RequestMethod.GET)
    public String updateUserView(Model model,@PathVariable(value = "uid",required = true) Integer uid){
        LinkedList<User> userArrayList = dataRepository.getUserArrayList();
        User updateUser = null;
        if (!userArrayList.isEmpty()){
            for (User user :userArrayList) {
                if (user.getUid().equals(uid)){
                    updateUser = user;
                    userArrayList.remove(user);
                    break;
                }
            }
        }
        model.addAttribute("userInfo", updateUser);
        return "update";
    }

    @RequestMapping(value = "/update/{uid}", method = RequestMethod.POST)
    public String updateUser(@PathVariable(value = "uid",required = true) Integer uid,
                             @RequestParam(value = "firstName", defaultValue = "") String firstName,
                             @RequestParam(value = "lastName", defaultValue = "") String lastName,
                             @RequestParam(value = "birthday", defaultValue = "Fri Feb 26 13:11:26 CST 2021") String birthday) throws ParseException {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !birthday.isEmpty()){
            LinkedList<User> userArrayList = dataRepository.getUserArrayList();
            userArrayList.addLast(new User(uid, firstName, lastName, new SimpleDateFormat("yyyy-MM-dd").parse(birthday)));
        }
        return "redirect:/";
    }

}
