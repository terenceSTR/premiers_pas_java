import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Personne{
protected String nom, prenom, adresse, tel;
public Personne (String n, String p, String ad, String t)

    {
        nom= n;
        prenom = p;
        adresse = ad;
        tel = t;
    }
}

class Gestion_Agenda
{
    protected Vector <Personne> vpers;
    public Gestion_Agenda()
    {
        vpers = new Vector<Personne>();
    }

    public void ajouterPersonne (String nm, String pn, String add, String tt){
        vpers.add(new Personne (nm,pn,add,tt));
    }
    public Personne rechercherPersonne (int i){
        return (vpers.get(i));
    }
}
class Fenetre extends JFrame implements ActionListener{
    private JTextField nom, prenom, adresse, telephone;
    private JButton bAjouter, bAfficherPrec, bAfficherSuiv, bQuitter;
    private Gestion_Agenda g;
    private int num_courant;

  
    public Fenetre(Gestion_Agenda gg)
    {
        setTitle ("Agenda");
        Container cf = this.getContentPane();
        cf.setLayout(new GridLayout(5,1));

        JPanel p1 = new JPanel();
        p1.add(new JLabel("nom"));
        nom = new JTextField (20);
        p1.add(nom);
        cf.add(p1);

        JPanel p2 = new JPanel ();
        p2.add(new JLabel("Prenom"));
        prenom = new JTextField(20);
        p2.add(prenom);
        cf.add(p2);

        JPanel p3 = new JPanel();
        p3.add (new JLabel("Adresse"));
        adresse = new JTextField(30);
        p3.add(adresse);
        cf.add(p3);

        JPanel p4 = new JPanel();
        p4.add (new JLabel("Telephone"));
        telephone = new JTextField(14);
        p4.add(adresse);
        cf.add(p4);

        JPanel p5 = new JPanel();
        bAjouter = new JButton ("Ajout");
        p5.add(bAjouter);
        bAfficherPrec = new JButton ("Aff Prec");
        p5.add(bAfficherPrec);
        bAfficherSuiv = new JButton("Aff Suiv");
        p5.add(bAfficherSuiv);
        bQuitter = new JButton("Quitter");
        p5.add(bQuitter);
        cf.add(p5);

        bAjouter.addActionListener(this);
        bAfficherPrec.addActionListener(this);
        bAfficherSuiv.addActionListener(this);
        bQuitter.addActionListener(this);

        g= gg;
        num_courant = 0;
    }
    public void actionPerformed(ActionEvent e )
    {
        Object source= e.getSource();
        if (source == bAjouter) ajouterPersonne();
        else if (source == bAfficherPrec) afficherPrecedent();
        else if (source == bAfficherSuiv) afficherSuivant();
        else if (source == bQuitter) quitter();
    }
    public void ajouterPersonne(){
        String n = nom.getText();
        String p = prenom.getText();
        String a = adresse.getText();
        String t = telephone.getText();
        g.ajouterPersonne(n,p,a,t);
        num_courant = g.vpers.size();
    }
     public void afficherPrecedent ()
     {
        int nn= num_courant - 1;
        if (nn>0 && nn < g.vpers.size())
        {
            Personne p = g.rechercherPersonne (nn);
            nom.setText(p.prenom);
            adresse.setText(p.adresse);
            telephone.setText(p.tel);
            num_courant --;
        }

        else
        num_courant = 0;
     }
     public void afficherSuivant(){
        int nn = num_courant + 1;
        if (nn>=0 && nn < g.vpers.size())
        {
            Personne p= g.rechercherPersonne (nn);
            nom.setText(p.nom);
            prenom.setText(p.prenom);
            adresse.setText(p.adresse);
            telephone.setText(p.tel);
            num_courant ++;
        }
        else {
            num_courant = g.vpers.size() - 1;
        }
     }

     public void quitter()
    {
        System.exit(0);
    }
}

public class main 
{
public static void main (String args[])
{
    Gestion_Agenda g1 = new Gestion_Agenda();
    Fenetre f1 = new Fenetre(g1);
    f1.pack();
    f1.setVisible(true);
}
}

