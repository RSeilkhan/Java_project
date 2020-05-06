package com.project.demo.controller;

import com.project.demo.entities.*;
import com.project.demo.repositories.*;
import com.project.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    NewsPostRepository newsPostRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CityRepository cityRepository;


    public User getUserData(){
        User userData = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof  AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            userData = userRepository.findByEmail(secUser.getUsername());
        }
        return userData;
    }


    //invites
   /* @RequestMapping("/deleteinvite/{user_id}")
    public String deleteInvite(@PathVariable("user_id") Long id, Model model, Principal principal) {
        // 1
        String email = principal.getName();
        User loggedUser = userService.findByEmail(email);
        Optional<User> inviting_user = userRepository.findById(id);

        return "redirect:/";
    }
    @RequestMapping("/cancelinvite/{user_id}")
    public String cancelInvite(@PathVariable("user_id") Long id, Principal principal) {
        //This is essentially the INVERSE of the delete invite route
        String current_user_email = principal.getName();
        org.springframework.security.core.userdetails.User loggedUser = uService.findByEmail(current_user_email);
        org.springframework.security.core.userdetails.User person_i_want_to_cancel_invite = uService.findOne(id);

        List<org.springframework.security.core.userdetails.User> i_invited = loggedUser.getInvitedFriends();

        i_invited.remove(person_i_want_to_cancel_invite);
        loggedUser.setInvitedFriends(i_invited);
        uService.save(loggedUser);

        return "redirect:/users";
    }


    @RequestMapping("/invite/{person_to_connect_id}")
    public String inviteUser(@PathVariable("person_to_connect_id") Long id, Principal principal){
        System.out.println("In /INVITE route");
        String email = principal.getName();
        org.springframework.security.core.userdetails.User loggedUser = uService.findByEmail(email);
        System.out.println("Inside INVITE route and LOGGED USER IS: " + loggedUser);
        org.springframework.security.core.userdetails.User connect_to_person = uService.findOne(id);
        System.out.println("The person " + loggedUser.getName() + " is trying to connect with is " + uService.findOne(id).getName());
        System.out.println("The logged friends amount is: " + loggedUser.getUserFriends().size());

        if(loggedUser.getInvitedFriends().size() == 0) {
            List<org.springframework.security.core.userdetails.User> list = new ArrayList<>();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        } else {
            List<org.springframework.security.core.userdetails.User> list = loggedUser.getInvitedFriends();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        }

        uService.save(loggedUser);
        return "redirect:/users";
    }
    // I may be able to consolidate this route into the other invite route
    @RequestMapping("/profile/invite/{person_to_connect_id}")
    public String profileInviteUser(@PathVariable("person_to_connect_id") Long id, Principal principal){
        System.out.println("In /INVITE route");
        String email = principal.getName();
        org.springframework.security.core.userdetails.User loggedUser = uService.findByEmail(email);
        System.out.println("Inside INVITE route and LOGGED USER IS: " + loggedUser);
        org.springframework.security.core.userdetails.User connect_to_person = uService.findOne(id);
        System.out.println("The person " + loggedUser.getName() + " is trying to connect with is " + uService.findOne(id).getName());
        System.out.println("The logged friends amount is: " + loggedUser.getUserFriends().size());

        if(loggedUser.getInvitedFriends().size() == 0) {
            List<org.springframework.security.core.userdetails.User> list = new ArrayList<>();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        } else {
            List<org.springframework.security.core.userdetails.User> list = loggedUser.getInvitedFriends();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        }

        uService.save(loggedUser);
        //Stringifying to mkae it work in URI
        String string_person_to_connect_id = id.toString();
        return "redirect:/users/".concat(string_person_to_connect_id);
    }
    @RequestMapping("/profile/cancelinvite/{user_id}")
    public String profileCancelInvite(@PathVariable("user_id") Long id, Principal principal) {
        //This is essentially the INVERSE of the delete invite route
        String current_user_email = principal.getName();
        org.springframework.security.core.userdetails.User loggedUser = uService.findByEmail(current_user_email);
        org.springframework.security.core.userdetails.User person_i_want_to_cancel_invite = uService.findOne(id);

        List<org.springframework.security.core.userdetails.User> i_invited = loggedUser.getInvitedFriends();

        i_invited.remove(person_i_want_to_cancel_invite);
        loggedUser.setInvitedFriends(i_invited);
        uService.save(loggedUser);
        //Stringifying so it works in URI
        String string_person_to_connect_id = id.toString();
        return "redirect:/users/".concat(string_person_to_connect_id);
    }

*/



    @PostMapping(value = "/search") //search and filter
    public String search(ModelMap model,
                         @RequestParam(name = "age_from") int age_from,
                         @RequestParam(name = "age_to") int age_to,
                         @RequestParam(name = "gender") String gender){


       // Optional<User> user = userRepository.findByFilter(gender, age_from, age_to);
        //model.addAttribute("filter_users", user);

//        List<Users> user  = userRepository.findAll();
//        Optional<Users> u_city = userRepository.findByCity(city);
//
//
//        @Query("SELECT * FROM User u t_users WHERE u.gender =  ")
//        List<User> findByFilter(String user);

        return "index";
    }


    @GetMapping(value = "/users/{email}") //visit profiles
    public String visit(ModelMap model, @PathVariable(name = "email") String email){

        User user  = userRepository.findByEmail(email);

        model.addAttribute("allUsers",  user);

        return "visit_profile";
    }


    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/profile")
    public String account(ModelMap model){
        List<City> city = cityRepository.findAll();
        model.addAttribute("city", city);

      //  Optional<Users> users = userRepository.findById(id);
        model.addAttribute("user", getUserData());



        //id, email, password, name, surname, isactive(bool), roles, gender, city, telnumber, birthday

        return "profile";
    }


    @GetMapping(value = "/auth_reg")
    public String auth_reg(ModelMap model){
        List<City> city = cityRepository.findAll();
        model.addAttribute("city", city);
        return "auth_reg";
    }

    @PostMapping(value = "/register")
    public String register( @RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "surName") String surName,
                           @RequestParam(name = "gender") String gender,
                           @RequestParam(name = "birthday") Integer birthday,
                           @RequestParam(name = "tel_number") String tel_number,
                           @RequestParam(name = "u_city") Long u_city,
                           @RequestParam(name = "about") String about,
                            @RequestParam(name = "avatar") String avatar


    )
    {
        String redirect = "redirect:/auth_reg?registration_error";

        User user = userRepository.findByEmail(email);
        if(user==null && password.length()>6 && email!=null && password!=null){
            Set<City> city = new HashSet<>();
            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.getOne(1L); //1 способ
            City userCity = cityRepository.getOne(u_city); //1 способ

            roles.add(userRole);
            city.add(userCity);

            user = new User(null, email, password, name, surName, true, roles, gender, city, tel_number, about, birthday, avatar, null, null,null, null, null);
            userService.registerUser(user);
                redirect = "redirect:/auth_reg?registration_success";

        }

        return redirect;
    }

    @PostMapping(value = "/refpass")
    public String refpass(@RequestParam(name = "id") Long id,
                          @RequestParam(name = "password") String password){

        String redirect = "redirect:/profile?password_not_refreshed";

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setPassword(password);
            userService.registerUser(user.get());
            redirect = "redirect:/profile?password_refreshed";
        }

        return redirect;
    }

    @GetMapping(value = "/edit_profile")
    public String edit_profile(ModelMap model){
        model.addAttribute("user", getUserData());

        return "edit_profile";
    }

    @PostMapping(value = "/editprof")
    //@PreAuthorize("#user_id == principal.userId")
    public String editprof(ModelMap model, @RequestParam(name = "id") Long id,
                           @RequestParam(name = "email") String email,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "surname") String surname,
                           @RequestParam(name = "gender") String gender,
                           @RequestParam(name = "birthday") Integer birthday,
                           @RequestParam(name = "tel_number") String tel_number,
                           //@RequestParam(name = "u_city") Long u_city,
                           @RequestParam(name = "about") String about,
                           @RequestParam(name = "avatar") String avatar

                           ){

        String redirect = "redirect:/edit_profile?profile_not_refreshed";
        model.addAttribute("user", getUserData());

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setName(name);
            user.get().setEmail(email);
            user.get().setSurname(surname);
            user.get().setGender(gender);
            user.get().setTel_number(tel_number);
          //  user.get().setCity(u_city); //как закидывать если сити из другой базы
            user.get().setAbout(about);
            user.get().setBirthday(birthday);
            user.get().setAvatar(avatar);



            userService.registerUser(user.get());
            redirect = "redirect:/profile?password_refreshed";
        }

        return redirect;
    }

    @GetMapping(value = "/profile_admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String profile_admin(ModelMap model){

        List<User> allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);

        Role moderator = roleRepository.getOne(3L);
        model.addAttribute("moderator", moderator);

        Role admin = roleRepository.getOne(2L);
        model.addAttribute("admin", admin);

        return "profile_admin";
    }

    @GetMapping(value = "/profile_moderator")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String profile_moderator(ModelMap model){

        List<User> allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);

        Role moderator = roleRepository.getOne(3L);
        model.addAttribute("moderator", moderator);

        Role admin = roleRepository.getOne(2L);
        model.addAttribute("admin", admin);

        return "profile_moderator";
    }

    @PostMapping(value = "/addUserModerator")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUserModerator(@RequestParam(name = "email") String email,
                                   @RequestParam(name = "MODERATOR") int moderator_role){

        String redirect = "redirect:/profile_admin?user/moderator_added_error";

        User user = userRepository.findByEmail(email);
        if(user==null){

            Set<Role> roles = new HashSet<>();
            if(moderator_role==1)
                roles.add(roleRepository.getOne(3L));

            //user = new Users(null, email, password, name, surName, true, roles);
            userService.registerUser(user);
            redirect = "redirect:/profile_admin?user/moderator_added_success";

        }

        return redirect;
    }


    @PostMapping(value = "/addUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@RequestParam(name = "email") String email,
                                   @RequestParam(name = "USER") int user_role){

        String redirect = "redirect:/profile_admin?user/user_added_error";

        User user = userRepository.findByEmail(email);
        if(user==null){

            Set<Role> roles = new HashSet<>();
            if(user_role==1)
                roles.add(roleRepository.getOne(1L));

            //user = new Users(null, email, password, name, surName, true, roles);
            userService.registerUser(user);
            redirect = "redirect:/profile_admin?user/moderator_added_success";

        }

        return redirect;
    }


    @PostMapping(value = "/refPassword")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String refPassword(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "password") String password){

        String redirect = "redirect:/profile_admin?password_not_refreshed";

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setPassword(password);
            userService.registerUser(user.get());
            redirect = "redirect:/profile_admin?password_refreshed";
        }

        return redirect;
    }

    @PostMapping(value = "/blockUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String blockUser(@RequestParam(name = "id") Long id){

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setIsActive(false);
            userRepository.save(user.get());
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            User requester = userRepository.findByEmail(secUser.getUsername());
            if(requester.getRoles().contains(roleRepository.getOne(3L)))
                return "redirect:/profile_moderator";
        }


        return "redirect:/profile_admin";
    }

    @PostMapping(value = "/unBlockUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String unBlockUser(@RequestParam(name = "id") Long id){

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setIsActive(true);
            userRepository.save(user.get());
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            User requester = userRepository.findByEmail(secUser.getUsername());
            if(requester.getRoles().contains(roleRepository.getOne(3L)))
                return "redirect:/profile_moderator";
        }

        return "redirect:/profile_admin";
    }

    ///////////////////////////////END USER//////////////////////////////////////////

    //////////////////////NEWS POST///////////////////////////////////////////////////////

    @GetMapping(value = "/")
    public String index(ModelMap model){

        List<NewsPost> allNews = newsPostRepository.findAll();
        model.addAttribute("allNews", allNews);
     /*   Optional<Users> user = userRepository.findById(id);
        model.addAttribute("user", user.orElse(new Users(null, null, null, null,null, null, null, null, null, null, null, null)));
*/
        List<User> user = userRepository.findAll();
        model.addAttribute("allUsers", user);

        return "index";
    }

    @PostMapping(value = "/addPost")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String addPost(@RequestParam(name = "title") String title,
                          @RequestParam(name = "shortContent") String shortContent,
                          @RequestParam(name = "content") String content){

//        Users author = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//berem avtorizovanny user, nuzno razobratsya kak eto pashet

        User author = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            author = userRepository.findByEmail(secUser.getUsername());
        }

        newsPostRepository.save(new NewsPost(null, title, shortContent, content, author, new Date()));

        return "redirect:/";
    }

    @GetMapping(value = "/newsPage/{id}")
    public String newsPage(ModelMap model,
                           @PathVariable(name = "id") Long id){

        Optional<NewsPost> post = newsPostRepository.findById(id);
        //model.addAttribute("post", post.orElse(new NewsPost(null, "No Name", "No Name", "No Name", null, null)));
        model.addAttribute("post", post.orElse(new NewsPost(null, null, null, null, null, null)));

        Role moderator = roleRepository.getOne(3L);
        model.addAttribute("moderator", moderator);

        Role user = roleRepository.getOne(1L);
        model.addAttribute("user", user);

        List<Comment> allComments = commentRepository.findByNewsPostId(id);
        model.addAttribute("allComments", allComments);

        User adam = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            adam = userRepository.findByEmail(secUser.getUsername());
        }
        model.addAttribute("adam", adam);

        return "newsPage";
    }

    @PostMapping("/editPost")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String editPost(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "shortContent") String shortContent,
                           @RequestParam(name = "content") String content){

        Optional<NewsPost> post = newsPostRepository.findById(id);
        if(post.isPresent()){
            post.get().setTitle(title);
            post.get().setShortContent(shortContent);
            post.get().setContent(content);

            newsPostRepository.save(post.get());
        }


        return "redirect:/newsPage/"+id;
    }


    @PostMapping("/deletePost")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String deletePost(@RequestParam(name = "id") Long id){

        Optional<NewsPost> post = newsPostRepository.findById(id);
        if(post.isPresent()){
            newsPostRepository.delete(post.get());
        }

        return "redirect:/";
    }

    /////////////////////////////////COMMENT//////////////////////////////////////////////////////////

    @PostMapping(value = "/addComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addComment(@RequestParam(name = "postId") Long postId,
                             @RequestParam(name = "comment") String comment){

        User author = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            author = userRepository.findByEmail(secUser.getUsername());
        }



        commentRepository.save(new Comment(null, author, newsPostRepository.getOne(postId), comment, new Date()));

        return "redirect:/newsPage/"+postId;
    }


    @PostMapping(value = "/changeComment")
    public String changeComment(@RequestParam(name = "postId") Long postId,
                                @RequestParam(name = "comment_id") Long comment_id,
                                @RequestParam(name = "changedComment") String changedComment){

        Optional<Comment> comment = commentRepository.findById(comment_id);
        if(comment.isPresent()){
            comment.get().setComment(changedComment);
            commentRepository.save(comment.get());
        }

        return "redirect:/newsPage/"+postId;
    }

    @PostMapping(value = "/deleteComment")
    public String deleteComment(@RequestParam(name = "postId") Long postId,
                                @RequestParam(name = "comment_id") Long comment_id){

        commentRepository.delete(commentRepository.getOne(comment_id));

        return "redirect:/newsPage/"+postId;
    }
}
