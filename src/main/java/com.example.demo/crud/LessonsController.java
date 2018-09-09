package com.example.demo.crud;

import org.jboss.logging.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

   @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long id)
   {
       return this.repository.findById(id).get();

   }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id)
    {
         this.repository.deleteById(id);

    }

    @PatchMapping("/{id}")
    public Lesson patchLesson(@PathVariable Long id, @RequestBody Lesson lesson){
        Lesson thisLesson = repository.findById(id).get();

        thisLesson.setTitle(lesson.getTitle());
        thisLesson.setDeliveredOn(lesson.getDeliveredOn());

        return this.repository.save(thisLesson);
    }

    @GetMapping("/find/{title}")
    public Lesson findByTitle(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> findLessonsBetweenDates(
            @RequestParam(value = "date1") @DateTimeFormat(pattern="yyyy-MM-dd")Date date1,
            @RequestParam(value = "date2") @DateTimeFormat(pattern="yyyy-MM-dd")Date date2){
        return this.repository.findLessonsBetweenDates(date1,date2);
    }

}
