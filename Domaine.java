import java.util.ArrayList;
//les méthodes sont testées dans le main de Quiz.java
public class Domaine
{
    /** Liste des questions dans le sujet de matière (domaine) */
    private ArrayList<String> questions;

    /** Liste des réponses associés à chaque question */
    private ArrayList<String> answers;

    /** Liste des scores pour chaque question */
    private ArrayList<Integer> score;
  
    /** Liste pour indiquer si la questions est déjà répondue */
    private ArrayList<Boolean> qdone;
  
    /** Nom d'un domaine */
    private String sujet;
    
    /** Score total */
    private int totalscore;

    
    /** Constructeur */
    Domaine( String sujet )
    {
        this.sujet = sujet;
        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        score = new ArrayList<Integer>();
        qdone = new ArrayList<Boolean>();
    }


    /** Retourne le nom du domaine */
    public String getNom() 
    {
        return this.sujet;
    }


    /** Ajoute les questions dans le domaine */
    public void addQA( String question, String answer, int score )
    {
        if (this.questions.contains(question))
        {
            System.out.println( "Cette question est déjà dans la liste des questions");
        }
        else
        {
            this.questions.add(question);
            this.answers.add(answer);
            this.score.add(score);
            this.qdone.add(false);
        }
    }


    /** Retourne la liste des questions */
    public ArrayList<String> getQuestions()
    {
        return this.questions;
    }



    /** Retourne une question établie dans un indexe spécifique */  
    public String getQuestion( int indexe )
    {
        int index = indexe;     
        return this.questions.get(index);
    }


    /** Retourne la réponse établie dans un indexe spécifique */
    public String getAnswer( int index )
    {
        return this.answers.get(index);
    }


    /** Retourne le score établie dans un indexe spécifique */
    public int getScore(int index)
    {
        return this.score.get(index);
    }

    /** Score total après l'addition des scores de toutes les quesitons */
    public int totalScore()
    {
        totalscore = 0;
        for (int a : this.score)
        {
            this.totalscore += a;
        }
        return this.totalscore;
    }


        
    /** Enlever une question et ses données du domaine */
    public void removeQA(String question)
    {
        if (this.questions.contains(question))
        {
            int i = this.questions.indexOf(question);

            this.questions.remove(i);
            this.answers.remove(i);
            this.score.remove(i);
            this.qdone.remove(i); 

            System.out.println("\nStatut de cette question: supprimée du questionnaire");
        }
        else
        {
            System.out.println("Cette question n'est pas retrouvée dans le questionnaire");
        }
    }



    /** Affiche toutes les données dans le questionnaire */
    public void showData()
    {
        if (this.questions.size()> 0)
        {
            for ( int i = 0; i < this.questions.size(); i++)
            {
                int rank = i+1;
                System.out.println( "\n\nDonnées pour question " + rank 
                                + "\nQuestion:\t" + this.questions.get(i) 
                                + "\nRéponse:\t" + this.answers.get(i) 
                                + "\nScore:  \t" + this.score.get(i) );
            }   
        }
        
        else
        {
            System.out.println("Aucune question dans le questionnaire.");
        }
       
    }
}