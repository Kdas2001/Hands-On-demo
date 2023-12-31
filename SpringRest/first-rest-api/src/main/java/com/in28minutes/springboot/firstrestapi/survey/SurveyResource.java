package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//build rest api here
@RestController
public class SurveyResource {

	private SurveyService surveyService;

	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}
	
	// /surveys=> surveys
	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys(){
		return surveyService.retrieveAllSurveys();
	}
	
	@RequestMapping("/surveys/{surveyId}")
	public Survey retrieveSurveyById(@PathVariable String surveyId){
		Survey survey= surveyService.retrieveSurveyById(surveyId);
		if(survey==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;
	}
	
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId){
		List<Question> questions= surveyService.retrieveAllSurveyQuestions(surveyId);
		if(questions==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return questions;
	}
	
	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId){
		Question question = surveyService.retrieveSpecificSurveyQuestion
										(surveyId, questionId);
		
		if(question==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return question;
	}
}
