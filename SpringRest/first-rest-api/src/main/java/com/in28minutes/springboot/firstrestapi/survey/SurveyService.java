package com.in28minutes.springboot.firstrestapi.survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

//@service tells spring that this is a bean which they have to handle
@Service
public class SurveyService {
	
	private static List<Survey> surveys = new ArrayList<>();
	
	static {
	
		Question question1 = new Question("Question1",
		        "Most Popular Cloud Platform Today", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2",
		        "Fastest Growing Cloud Platform", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3",
		        "Most Popular DevOps Tool", Arrays.asList(
		                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
		        question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
		        "Description of the Survey", questions);

		surveys.add(survey);

		
	}

	

	public List<Survey> retrieveAllSurveys() {
		// TODO Auto-generated method stub
		return surveys;
	}



	public Survey retrieveSurveyById(String surveyId) {
		
		//the predicate is defining a lambda function
		//it checks if the surveyId matches
		Predicate<? super Survey> predicate=
				survey -> survey.getId().equalsIgnoreCase(surveyId);
		//we are converting the surveys into a stream and checking it one by one
		Optional<Survey> optionalSurvey
		       =surveys.stream().filter(predicate).findFirst();	
		// there will be at most one so we are using findFirst
		// also we are not sure if the element exists so we r using optional
		// optional is a container obj which may or may not have a non null value
		if(optionalSurvey.isEmpty()) return null;
		return optionalSurvey.get();
	}



	public List<Question> retrieveAllSurveyQuestions(String surveyId) {
		// TODO Auto-generated method stub
		Survey survey= retrieveSurveyById(surveyId);
		if(survey==null)  return null;
		return survey.getQuestions();	
		}

	public Question retrieveSpecificSurveyQuestion(String surveyId, String questionId) {

		List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);

		if (surveyQuestions == null)
			return null;

		Optional<Question> optionalQuestion = surveyQuestions.stream()
				.filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst();

		if (optionalQuestion.isEmpty())
			return null;

		return optionalQuestion.get();
	}


}