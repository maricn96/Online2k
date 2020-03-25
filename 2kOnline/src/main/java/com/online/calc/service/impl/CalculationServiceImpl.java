package com.online.calc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.online.calc.dto.CalculationDTO;
import com.online.calc.dto.CalculationToShowDTO;
import com.online.calc.dto.LoadMealsDTO;
import com.online.calc.dto.MealDTO;
import com.online.calc.model.Calculation;
import com.online.calc.model.Food;
import com.online.calc.model.Meal;
import com.online.calc.repository.CalculationRepository;
import com.online.calc.repository.FoodRepository;
import com.online.calc.repository.MealRepository;
import com.online.calc.service.CalculationService;
import com.online.calc.service.FoodService;

@Service
public class CalculationServiceImpl implements CalculationService{

	private final CalculationRepository calculationRepository;
	private final MealRepository mealRepository;
	
	private FoodService foodService;
	
	public CalculationServiceImpl(CalculationRepository calculationRepository, MealRepository mealRepository, FoodService foodService) {
		this.calculationRepository = calculationRepository;
		this.mealRepository = mealRepository;
		this.foodService = foodService;
	}
	
	@Override
	public Calculation getOneById(String id) {
		return calculationRepository.findById(new ObjectId(id)).get();
	}
	
	public Meal getOneMealById(ObjectId id) {
		return mealRepository.findById(id).get();
	}
	
	@Override
	public Boolean create(CalculationDTO dto) {
		Date date = new Date();
		List<Meal> meals = mealRepository.findByUser(new ObjectId(dto.getUser()));
		Set<ObjectId> foodNames = new HashSet<>();
		for(Meal meal : meals) {
			if(!meal.getSaved()) {
				foodNames.add(meal.getId());
				mealRepository.save(new Meal(meal.getId(), meal.getDateOfMeal(), meal.getUser(), meal.getAmount(), meal.getFood(), true));
			}
		}
		
		calculationRepository.save(new Calculation(foodNames, date, new ObjectId(dto.getUser()), false));
		return true;
	}
	
	/*
	 * Round number to 2 decimal places
	 * (non-Javadoc)
	 * @see com.online.calc.service.CalculationService#loadMeals(java.lang.String)
	 */
	@Override
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	/*
	 * Metoda koja vraca podatke za istoriju kalkulacija (moze da radi za malo obroka i kalkulacija, na veci broj ima da odziv bude 6 sekundi..)
	 * @see com.online.calc.service.CalculationService#getAllByUser(java.lang.String)
	 */
	@Override
	public List<CalculationToShowDTO> getAllByUser(String userid) {
		List<Calculation> calculations = calculationRepository.findByUser(new ObjectId(userid));
		List<CalculationToShowDTO> retVal = new ArrayList<>();
		List<Meal> meals = mealRepository.findByUser(new ObjectId(userid));

		double calories = 0;
		double proteins = 0;
		double carbs = 0;
		double fat = 0;
		for(Calculation calc : calculations) {
			for(ObjectId mealid : calc.getMeals()) {
				for(Meal meal : meals) {
					if(meal.getId().equals(mealid)) {
						Food food = foodService.getOneByName(meal.getFood()); //ovde pukne na ovoj liniji u trecoj iteraciji koji kurac
						if(food != null) {
							calories += (meal.getAmount()/100)*food.getCalories();
							proteins += (meal.getAmount()/100)*food.getProteins();
							carbs += (meal.getAmount()/100)*food.getCarbs();
							fat += (meal.getAmount()/100)*food.getFat();
						}
					}
				}
			}
			retVal.add(new CalculationToShowDTO(calc.getId().toString(), calc.getDateOfCalculation(), round(calories, 2), round(proteins, 2), round(carbs, 2), round(fat, 2)));
			calories = 0;
			proteins = 0;
			carbs = 0;
			fat = 0;
		}
		
		return retVal;
	}

	/*
	 * Kreiranje jednog obroka (meal)
	 * @see com.online.calc.service.CalculationService#createCurrentCalculation(com.online.calc.dto.MealDTO)
	 */
	@Override
	public Boolean createCurrentCalculation(MealDTO mealDto) {
		//za sad ovako parsiranje datuma, izdvoj u neku util klasu view-model 
//		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
//
//		String formattedDate = formatter.format(LocalDate.now());
//		Date date1 = null;
//		try {
////			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		System.out.println(formattedDate);
//		Date date = new Date();
		mealRepository.save(new Meal(null, new ObjectId(mealDto.getUser()), mealDto.getAmount(), mealDto.getFood(), false));
		return true;
	}
	
	
	/*
	 * Ucitavanje obroka (npr ako korisnik unese sad jedan, pa izadje iz aplikacije pa se posle seti da opet unese, dakle cuva prethodno stanje)
	 * @see com.online.calc.service.CalculationService#loadMeals(java.lang.String)
	 */
	@Override
	public LoadMealsDTO loadMeals(String userid) {
		List<Meal> meals = mealRepository.findByUser(new ObjectId(userid));
		
		double calories = 0;
		double proteins = 0;
		double carbs = 0;
		double fat = 0;
		
		for(Meal meal : meals) {
			if(!meal.getSaved()) {
				Food food = foodService.getOneByName(meal.getFood());
				calories += (meal.getAmount()/100)*food.getCalories();
				proteins += (meal.getAmount()/100)*food.getProteins();
				carbs += (meal.getAmount()/100)*food.getCarbs();
				fat += (meal.getAmount()/100)*food.getFat();
			}
		}
		
		LoadMealsDTO retVal = new LoadMealsDTO(round(calories, 2), round(proteins, 2), round(carbs, 2), round(fat, 2), new Date());
		
		return retVal;
	}

	/*
	 * Vraca sve nesacuvane obroke, sluzi za kreiranje nove finalne kalkulacije na klijentu
	 * @see com.online.calc.service.CalculationService#getAllUnsavedMeals(java.lang.String)
	 */
	@Override
	public List<MealDTO> getAllUnsavedMeals(String userid) {
		List<Meal> meals = mealRepository.findByUser(new ObjectId(userid));
		List<MealDTO> retVal = new ArrayList<>();
		
		for(Meal meal : meals) {
			if(!meal.getSaved()) 
				retVal.add(meal.asDto());
		}
		
		return retVal;
	}

	/*
	 * Vraca sve obroke na osnovu prosledjenog id-ja kalkulacije
	 * @see com.online.calc.service.CalculationService#getAllMealsByCalc(java.lang.String)
	 */
	@Override
	public List<MealDTO> getAllMealsByCalc(String id) {
		Calculation calculation = getOneById(id);
		List<MealDTO> retVal = new ArrayList<>();
		
		for(ObjectId mealid : calculation.getMeals()) {
			Meal meal = getOneMealById(mealid);
			retVal.add(new MealDTO(mealid, meal.getDateOfMeal(), meal.getUser().toString(), meal.getAmount(), meal.getFood(), meal.getSaved()));
		}
	
	
		return retVal;
	}

	@Override
	public Boolean deleteCalculationById(String id) {
		//first delete all meals
		Calculation calculation = calculationRepository.findById(new ObjectId(id)).get();
		for(ObjectId mealId : calculation.getMeals()) 
			mealRepository.deleteById(mealId);
		
		calculationRepository.deleteById(new ObjectId(id));
		
		return true;
	}

	@Override
	public List<CalculationDTO> getAllCalculations() {
		List<Calculation> calculations = calculationRepository.findAll();
		List<CalculationDTO> retVal = new ArrayList<>();
		
		for(Calculation calc: calculations) {
			retVal.add(calc.asDto());
		}
		
		return retVal;
	}
	
}
