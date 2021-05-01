package com.bootcamp.springboot.bootcampdemo.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
        import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

        import java.net.URI;
        import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.EntityModel;
        import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/static/users")
    public List<User> retrieveUsers() {
        return service.findAll();
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/address")
    public List<Address> retrieveAddress() {
        return null;//addressRepository.findAll();
    }

    @GetMapping("/users/company")
    public List<Company> retrieveCompany() {
        return null;//companyRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
//        Optional<Address> address = addressRepository.findById(id);
//        Optional<Company> company = companyRepository.findById(id);

        if(user==null)
            throw new UserNotFoundException("id-"+ id);


        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        EntityModel<Optional<User>> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("allUsers"));

        //HATEOAS

        return resource;
    }

    @GetMapping("/static/users/{id}")
    public EntityModel<Optional<User>> retrieveOneUser(@PathVariable int id) {
        Optional<User> user = Optional.ofNullable(service.findOne(id));

        if(user==null)
            throw new UserNotFoundException("id-"+ id);


        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        EntityModel<Optional<User>> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("allUsers"));

        //HATEOAS

        return resource;
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user==null)
            throw new UserNotFoundException("id-"+ id);
    }


    //
    // input - details of user
    // output - CREATED & Return the created URI

    //HATEOAS

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        // CREATED
        // /user/{id}     savedUser.getId()
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}