package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Food;
import com.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	public List<Food> viewAllFoodDetails(){
		return foodRepository.findAll();	
	}
	
	public Food findFoodInfo(int fid){
		Optional<Food> op = foodRepository.findById(fid);
		if(op.isPresent()) {
			Food fooditem = op.get();
			return fooditem;
		}else {
			return null;
		}
	}
	
	public String addFood(Food food){
		Optional<Food> fd = foodRepository.findById(food.getFid());
		if(fd.isPresent()) {
			return "Nothing added, food item details already exists!";
		}else {
			foodRepository.save(food);
			return "Food item details added successfully!";
		}		
	}
	
	public String updateFood(Food food) {
		Optional<Food> fd = foodRepository.findById(food.getFid());
		if(fd.isPresent()) {
			Food fchange = fd.get();
			fchange.setFid(food.getFid());
			fchange.setFoodname(food.getFoodname());
			fchange.setDescription(food.getDescription());
			fchange.setImage(food.getImage());
			fchange.setPrice(food.getPrice());
			foodRepository.saveAndFlush(fchange);
			return "Food item details changed successfuly!";
			
		}else {
			return "Food item details not found!";
		}
	}
	
	public String deleteFood(int fid){
		Optional<Food> food = foodRepository.findById(fid);
		if(food.isPresent()) {
			foodRepository.deleteById(fid);
			return "Food item details deleted successfully!";
			
		}else {
			return "Food item details not found!";
		}
	}

}
