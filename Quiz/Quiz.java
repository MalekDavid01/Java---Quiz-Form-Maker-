package Quiz;
import java.util.Scanner;

public class Quiz
{
    /** Variable static de champ private pour insérer une donnée */
    private static Scanner clavier = new Scanner( System.in );

    /** Instance de domaine initalisé avec un String vide */
    private static Domaine actif = new Domaine("");

    /** Instance de Student initalisé avec un String vide */
    private static Student user= new Student("");


    public static void main(String[] args) 
    {
        //Variable du choix de l'utilisateur pour les options du menu
        String choix;

        //Booléen qui sert à controller la boucle du jeu
        boolean onJoue = true;

        /**Menu des options */
        String menu = 
            "\n\n" 
            + "Choisir une des options ci-dessous[1-5]. Si tu formes "
            + "le questionnaire, les options [1-3] sont utiles pour "
            + "l'établir.\n"
            +"\t1 - Ajouter au questionnaire.\n"
            +"\t2 - Enlever du questionnaire.\n"
            +"\t3 - Afficher les données du questionnaire crée.\n"
            +"\t4 - Répondre au questionnaire (évaluation): " + actif.getNom() + "\n"
            +"\t5 - Quitter le questionnaire.\n";

        /**Mots d'introduction */
        System.out.println("\n\nBienvenue sur une plateforme pour créer un questionnaire et ensuite y répondre!");
       
            
        //Nommer le sujet du domaine
        System.out.println("\n\nCréer un domaine pour le questionnaire");
        System.out.println("Nommer le sujet du domaine> ");
        String n = clavier.nextLine();
        actif = new Domaine( n );
            
        //message de confirmation
        System.out.println("Sujet de domaine '" + actif.getNom() + "' créé"); 

        
        /**boucle du jeu*/
        while ( onJoue )
        {
            System.out.println( menu ); //afficher les options dans le menu
            choix = "a"; //initialise la variable choix
            
            while("123456".indexOf( choix ) == -1 )
            {
                System.out.print("> ");
                choix = clavier.nextLine();
            }

            //Ajouter les données dans le questionnaire
            if (choix.equals("1"))
            {
                /**Booléen pour juger si l'utilisateur fini d'ajouter des questions */
                boolean done = false;
                addData(); //appelle la fonction pour ajouter les donnés d'une première question

                /**boucle pour ajouter des questions tant que l'utilisateur le désir*/
                while ( !done )
                { 
                    System.out.println("\n\nVoulez-vous ajouter encore des questions? répondre oui ou non");
                    String input = clavier.nextLine().toLowerCase();

                    if (input.equals("oui"))
                    {
                        addData();
                    }
                    else if (input.equals("non"))
                    {
                        System.out.println("\nVous avez terminé la création du domaine " 
                            + actif.getNom());
                        done = true; //quitte la boucle
                    }
                    else 
                    {
                        System.out.println("\nJe n'ai pas compris, il semble que vous "
                            +"n'avez pas bien répondu à la question,\nSVP répondre à nouveau");
                    }
                }
            }



            //Supprimer une question et toutes ses données du questionnaire
            else if (choix.equals("2"))
            {
                if (actif.getQuestions().size()>0)
                {
                    System.out.println("\nÉcrire la question duquel vous voulez enlever les données:\n");
                    String remove = clavier.nextLine();
                    removeData(remove); 
                }
                else
                {
                    System.out.println("Aucune question dans le questionnaire.");
                }
                
            }



            //Afficher les données du questionnaire
            else if (choix.equals("3"))
            {
                if (actif.getQuestions().size()>0)
                {
                    actif.showData();
                }

                else
                {
                    System.out.println("Aucune donnée dans le questionnaire");
                }
                
            }


            //Répondre au questionnaire (comme étant élève)
            else if (choix.equals("4"))
            {
                giveQuiz(); 
            }

            else if (choix.equals("5"))
            {
                System.out.println("Fin du formulateur de questionnaire.");
                onJoue = false; // quitter
            }
        }
    }

    /**Ajouter les données au questionnaire*/
    public static void addData()
    {
        //Établir une question, une réponse et un score
        System.out.println("\nÉtablir une question: ");
        String question = clavier.nextLine();
        
        System.out.println("\nÉtablir une réponse à cette question: ");
        String answer = clavier.nextLine();
        
        System.out.println("\nÉtablir un score à cette question: ");
        int points = Integer.parseInt(clavier.nextLine());
        actif.addQA(question, answer, points);
    }


    /** Supprimer une question et ses données */
    public static void removeData(String question)
    {
        actif.removeQA(question);
    }
        
    /** Méthode pour donner le quiz à un élève */
    public static void giveQuiz()
    {
        if (actif.getQuestions().size()>0) //vérifie s'il y a des questions dans le questionnaire
        {
            System.out.println("\nSession pour répondre au questionnaire");
            
            System.out.println("Nom de la personne qui répond au questionnaire> ");
            String username = clavier.nextLine();
            user = new Student( username );
            
            int questnum =0;
            for ( int index = 0; index <=actif.getQuestions().size()-1; index++ )
            {
                questnum++;      
                String ask = actif.getQuestion( index );
                System.out.println( "\nLa question "+ questnum + ":\n"+ ask );
                System.out.println( "Votre réponse> " );
                String response = clavier.nextLine();

                if (response.equals(actif.getAnswer(index)))
                {
                    System.out.println( "Bravo! C'est la bonne réponse!\n" );
                    user.addToScore(actif.getScore(index)); //ajouter au score personnel de l'utilisateur
                }

                else
                {
                    System.out.println("\nMauvaise réponse... La bonne réponse est: " + actif.getAnswer(index));
                } 
            }
            System.out.println("Vous avez terminé de répondre au questionnaire.");
            System.out.println(user + "/" + actif.totalScore()); //rapport entre score de l'utilisateur et le score total   
        }
        else
        {
            System.out.println("Aucune question dans le quesitonnaire. Ajouter des questions à travers l'option 1.");
        }
    }
}
