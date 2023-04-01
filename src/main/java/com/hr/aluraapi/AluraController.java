package com.hr.aluraapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return "Hello Java !!!!!!";
    }

    @GetMapping(value = "/languages")
    public List<Language> getLanguages() {
//        return languages;
//        List <Language> lang = repo.findAll();
//        return lang;
        return repo.findByOrderByRanking();
    }

    @PostMapping(value ="/languages")
    public ResponseEntity<Language> storeLanguage(@RequestBody Language language) {
        Language savedLanguage = repo.save(language);
        List <Language> res = new ArrayList<>();
        res.add(language);
        res.add(savedLanguage);
        return new ResponseEntity<>(savedLanguage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/language/{id}")
    public Language getLanguage(@PathVariable String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                                    //lamdba funtion to return exception
    }

    @PutMapping(value ="/language/{id}")
    public Language editLanguage(@RequestBody Language language,@PathVariable String id) {

//        Language languageEdited = repo.save(language);
//        return languageEdited;

//        repo.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        language.setId(id);
        return repo.save(language);

    }

    @DeleteMapping(value = "/language/{id}")
    public Boolean deleteLanguage(@PathVariable String id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
        return true;
    }


}
