package com.hr.aluraapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AluraController {


//    private List<Language> languages =
//            List.of(
//                    new Language( "Java","https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_512x512.png",1),
//                    new Language( "PHP","https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/php/php_512x512.png",2),
//                    new Language( "C","https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/c/c_512x512.png",3)
//            );

    @Autowired
    private LanguageRepository repo;

    @GetMapping(value = "/")
    public String index() {
        return "Hello Java !!!";
    }

    @GetMapping(value = "/languages")
    public List<Language> getLanguages() {
//        return languages;
//        List <Language> lang = repo.findAll();
//        return lang;
        return repo.findAll();
    }

    @PostMapping(value ="/languages")
    public List<Language> storeLanguage(@RequestBody Language language) {
        Language savedLanguage = repo.save(language);
        List <Language> res = new ArrayList<>();
        res.add(language);
        res.add(savedLanguage);
        return res;
    }


}
