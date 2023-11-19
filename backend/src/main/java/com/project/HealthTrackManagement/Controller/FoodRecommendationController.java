package com.project.HealthTrackManagement.Controller;

import com.project.HealthTrackManagement.Dto.FoodRecommendationDto;
import com.project.HealthTrackManagement.Model.FoodRecommendation;
import com.project.HealthTrackManagement.Service.Impl.FoodRecommendationService;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/foodRecommendation")
public class FoodRecommendationController
{
    @Autowired
    private FoodRecommendationService foodRecommendationService;

    @PostMapping
    private ResponseEntity<?> addFoodRecommendation(String _id, String name, String categories, String timings, Integer noOfCalories, MultipartFile image)
    {
        String filepath = Paths.get("").toAbsolutePath().toString();
        Path originalFilepath = Paths.get(filepath, "src", "main", "resources", "static", "Images", image.getOriginalFilename());

        String host = "http://localhost:8080/Images/" + image.getOriginalFilename();

        try {
            image.transferTo(originalFilepath);
        }
        catch (IOException ioe) {
            return new ResponseEntity<>("Failed to save exercise Images. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FoodRecommendation foodRecommendation = FoodRecommendation.builder()
                ._id(_id)
                .name(name)
                .categories(categories)
                .timings(timings)
                .noOfCalories(noOfCalories)
                .image(host)
                .build();
        try {
            foodRecommendationService.addFoodRecommendation(foodRecommendation);
            return new ResponseEntity<>("Food Recommendation Added Successfully", HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error adding Food Recommendation to the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<?> getAllFoodRecommendations()
    {
        try {
            List<FoodRecommendation> foodRecommendations = foodRecommendationService.getAllFoodRecommendation();

            List<FoodRecommendationDto> allFoodRecommendation = foodRecommendations.stream().map(this::convertToDto).collect(Collectors.toList());

            return new ResponseEntity<>(allFoodRecommendation, HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>("Error accessing the database.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private FoodRecommendationDto convertToDto(FoodRecommendation foodRecommendation) {
        FoodRecommendationDto foodRecommendationDto = new FoodRecommendationDto();

        foodRecommendationDto.set_id(foodRecommendation.get_id());
        foodRecommendationDto.setImage(foodRecommendation.getImage());
        foodRecommendationDto.setName(foodRecommendation.getName());
        foodRecommendationDto.setCategories(foodRecommendation.getCategories());
        foodRecommendationDto.setTimings(foodRecommendation.getTimings());
        foodRecommendationDto.setNoOfCalories(foodRecommendation.getNoOfCalories());

        return foodRecommendationDto;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getFoodRecommendationById(@PathVariable String id)
    {
        try
        {
            FoodRecommendation foodRecommendation = foodRecommendationService.getFoodRecommendationById(id);

            FoodRecommendationDto foodRecommendationDto = new FoodRecommendationDto();

            foodRecommendationDto.set_id(foodRecommendation.get_id());
            foodRecommendationDto.setImage(foodRecommendation.getImage());
            foodRecommendationDto.setName(foodRecommendation.getName());
            foodRecommendationDto.setCategories(foodRecommendation.getCategories());
            foodRecommendationDto.setTimings(foodRecommendation.getTimings());
            foodRecommendationDto.setNoOfCalories(foodRecommendation.getNoOfCalories());

            return new ResponseEntity<>(foodRecommendationDto,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Food Recommendations is not found for provided id is " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{categories}")
    private ResponseEntity<?> getFoodRecommendationByCategories(@PathVariable String categories)
    {
        try
        {
            FoodRecommendation foodRecommendation = foodRecommendationService.getFoodRecommendationByCategories(categories);
            return new ResponseEntity<>(foodRecommendation,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("No Food Recommendations found for provided categories"+categories,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<?> updateFoodRecommendation(@RequestBody FoodRecommendation foodRecommendation)
    {
        try
        {
            foodRecommendationService.updateFoodRecommendation(foodRecommendation);
            return new ResponseEntity<>("Food Recommendation updated Successfully",HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("An Error Occurring while updating the Food Recommendation details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFoodRecommendationById(@PathVariable String id)
    {
        try
        {
            foodRecommendationService.deleteFoodRecommendationById(id);
            return new ResponseEntity<>("Food Recommendation Deleted Successfully"+id,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Provided Food recommendation id is not found"+id,HttpStatus.NOT_FOUND);
        }
    }
}
