package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Dto.ExerciseRecommendationDto;
import com.project.HealthTrackManagement.Model.ExerciseRecommendation;
import com.project.HealthTrackManagement.Service.Impl.ExerciseRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exerciseRecommendation")
public class ExerciseRecommendationController
{
    @Autowired
    private ExerciseRecommendationService exerciseRecommendationService;

    @PostMapping
    private ResponseEntity<?> addExerciseRecommendation(String _id,String exerciseName, MultipartFile exerciseImages,Integer height,Integer weight,Integer noOfCalories)
    {
        String filepath = Paths.get("").toAbsolutePath().toString();
        Path originalFilepath = Paths.get(filepath, "src", "main", "resources", "static", "Images", exerciseImages.getOriginalFilename());

        String host = "http://localhost:8080/Images/" + exerciseImages.getOriginalFilename();

        try {
            exerciseImages.transferTo(originalFilepath);
        }
        catch (IOException ioe) {
            return new ResponseEntity<>("Failed to save exercise Images. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ExerciseRecommendation exerciseRecommendation = ExerciseRecommendation.builder()
                ._id(_id)
                .exerciseName(exerciseName)
                .weight(weight)
                .height(height)
                .noOfCalories(noOfCalories)
                .exerciseImages(host)
                .build();
        try {
            exerciseRecommendationService.addExerciseRecommendation(exerciseRecommendation);
            return new ResponseEntity<>("Exercise Recommendation Added Successfully", HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error adding Exercise Recommendation to the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<?> getAllExerciseRecommendation()
    {
        try {
            List<ExerciseRecommendation> exerciseRecommendations = exerciseRecommendationService.getAllExerciseRecommendation();

            List<ExerciseRecommendationDto> allExerciseRecommendation = exerciseRecommendations.stream().map(this::convertToDto).collect(Collectors.toList());

            return new ResponseEntity<>(allExerciseRecommendation, HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error accessing the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ExerciseRecommendationDto convertToDto(ExerciseRecommendation exerciseRecommendation) {
        ExerciseRecommendationDto exerciseRecommendationDto = new ExerciseRecommendationDto();

        exerciseRecommendationDto.set_id(exerciseRecommendation.get_id());
        exerciseRecommendationDto.setExerciseName(exerciseRecommendation.getExerciseName());
        exerciseRecommendationDto.setExerciseImages(exerciseRecommendation.getExerciseImages());
        exerciseRecommendationDto.setWeight(exerciseRecommendation.getWeight());
        exerciseRecommendationDto.setHeight(exerciseRecommendation.getHeight());
        exerciseRecommendationDto.setNoOfCalories(exerciseRecommendation.getNoOfCalories());

        return exerciseRecommendationDto;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getExerciseRecommendationsById(@PathVariable String id)
    {
        try
        {
            ExerciseRecommendation exerciseRecommendation = exerciseRecommendationService.getExerciseRecommendationById(id);

            ExerciseRecommendationDto exerciseRecommendationDto = new ExerciseRecommendationDto();

            exerciseRecommendationDto.set_id(exerciseRecommendation.get_id());
            exerciseRecommendationDto.setExerciseName(exerciseRecommendation.getExerciseName());
            exerciseRecommendationDto.setExerciseImages(exerciseRecommendation.getExerciseImages());
            exerciseRecommendationDto.setWeight(exerciseRecommendation.getWeight());
            exerciseRecommendationDto.setHeight(exerciseRecommendation.getHeight());
            exerciseRecommendationDto.setNoOfCalories(exerciseRecommendation.getNoOfCalories());

            return new ResponseEntity<>(exerciseRecommendationDto,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Exercise Recommendations is not found for provided id is " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{height}/{weight}")
    private ResponseEntity<?> getExerciseRecommendationByHeightAndWeight(@PathVariable Integer height,@PathVariable Integer weight)
    {
        try
        {
            List<ExerciseRecommendation> exerciseRecommendation = exerciseRecommendationService.getExerciseRecommendationByHeightAndWeight(height,weight);
            return new ResponseEntity<>(exerciseRecommendation,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(Map.of("msg" , e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    private ResponseEntity<?> updateExerciseRecommendation(@RequestBody ExerciseRecommendation exerciseRecommendation)
    {
        try
        {
            exerciseRecommendationService.updateExerciseRecommendation(exerciseRecommendation);
            return new ResponseEntity<>("Exercise Recommendation updated Successfully",HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("An Error Occurring while updating the Exercise Recommendation details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteExerciseRecommendation(@PathVariable String id)
    {
        try
        {
            exerciseRecommendationService.deleteExerciseRecommendation(id);
            return new ResponseEntity<>("Exercise Recommendation Deleted Successfully"+id,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Provided exercise recommendation id is not found"+id,HttpStatus.NOT_FOUND);
        }
    }
}
