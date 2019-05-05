/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.test;

/**
 *
 * @author jorda
 */
public class Question {
    
    private Option[] options = new Option[4];
    private int answer = 0;
    private String Question;
    
    public Question(String Question){
        this.Question = Question;
    }
    
    /*
    * writeToDB method
    *gets the selected option
    *if option does not equal null, writes the option to the Database
    *if option does equal null, return false
    */
    public boolean writeToDB(){
        Option option = getSelectedOption();
        // make sure an option is selected
        if(option != null){
            return option.writeToDB();
        }
        return false;
    }
    
    public boolean addQuestionToDB(){
        //add question and options to database with correct answer
        // int auto_increment index, string Question, string option1, string option2, string option3, string option4, int answer 
        String[] optionsText = new String[options.length];
        for(int i = 0; i < options.length; i++){
            optionsText[i] = options[i].getText();
        }  
        
        return false;
    }
    /*
    * createOption method
    * Adds the option to an array
    * if options equals null, add the option to the array and return true.
    * if options does not equal null, return false.
    */
    public boolean createOption(String text){
        Option option = new Option(this, text);
        for(int i = 0; i < options.length; i++){
            if(options[i] == null){
                options[i] = option;
                return true;
            }
        }
        return false;
    }
    /*
    * getSelectedOption method
    *loops through all options
    *if option is checked it returns the option
    *if option is false, returns null value
    */
    public Option getSelectedOption(){
        for(int i = 0; i < options.length; i++){
            if(options[i].isChecked()) return options[i];
        }
        return null;
    }
    
    /*
    * selectionOption method
    * Takes index parameter, marks options of selected index
    * to true, and all other to false
    * returns true if option is selected and returns false
    * if finds null value
    */
    public boolean selectOption(int index){
        for(int i = 0; i < options.length; i++){
            if(options[index] == null) break;
            if(i != index) options[i].setChecked(false);
            else{
                options[i].setChecked(true);
                return true;
            }
        }
        return false;
    }
    
    public Option[] getOptions() {
        return options;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }
    
    public void setAnswer(int answer){
        this.answer = answer;
    }
    
    public int getAnswer(){
        return answer;
    }
 
    
    
    
}
