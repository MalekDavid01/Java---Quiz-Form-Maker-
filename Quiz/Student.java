package Quiz;
//les méthodes sont testées dans le main de Quiz.java
public class Student 
{    
    /** Score de l'élève */
    private int score;

    /** Nom de l'étudiant*/
    private String name;


    /** Constructeur */
    Student(String nom)
    {
        name= nom;
        score = 0; //commence avec un score de 0
    }


    /** Retourne le nom de l'élève */
    public String getNom()
    {
        return this.name;
    }

    /** Ajoute des points au score de l'élève */
    public void addToScore(int score) 
    {
        this.score += score;
    }

    /** Retourne le score de l'élève */
    public int getScore()
    {
        return this.score;
    }

    /** Retourner le résultat de l'élève après qu'il répond au questionnaire */
    public String toString()
    {
        String str = ("Élève:\t" + this.getNom() + "\nScore:\t" + this.getScore()); 
        return str;
    }


}
