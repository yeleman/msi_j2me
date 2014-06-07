
package msisr;

import javax.microedition.lcdui.*;
import msisr.Configuration.*;
import msisr.Constants.*;

/**
 * J2ME Form displaying a long help text
 * Instanciated with a section paramater
 * which triggers appropriate text.
 * @author rgaudin
 */
public class HelpForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);

    private StringItem helpText;
    MSIMIDlet midlet;
    Displayable returnTo;

public HelpForm(MSIMIDlet midlet, Displayable d, String section) {
    super("Aide");
    this.midlet = midlet;
    this.returnTo = d;

    this.getContentFromSection(section);

    append(helpText);
    addCommand(CMD_EXIT);
    this.setCommandListener (this);
  }

/*
 *
 */
private void getContentFromSection(String section) {
    String text;

    if (section.equalsIgnoreCase("passwd")) {
        text = "Renseignez votre identifiant et votre ancien mot de passe dans les champs adéquat.\n" +
               "Ensuite, indiquez le nouveau mot de passe désiré. Celui-ci doit faire au moins 3 caractères.\n" +
               "Vous recevrez un SMS du serveur confirmant ou non le changement de mot de passe.\n\n" +
               "En cas de problème, contactez ANTIM.";
    } else if (section.equalsIgnoreCase("mainmenu")) {
        text = "Chaque élément de la liste correspond à une section du formulaire.\n" +
               "Entrez dans chaque et renseignez les champs en copiant le papier.\n" +
               "Dès qu'une section est complète et sans erreur, son nom contient [OK].\n\n" +
               "En cas de problème, contactez ANTIM.";
    } else if (section.equalsIgnoreCase("provided_services")) {
        text = "Renseignez uniquement les données du rapport des Services Prestés.\n" +
               "Indiquez uniquement les quantités et non les CAP.\n\n" +
               "En cas de problème, contactez votre District de référence.";
    } else if (section.equalsIgnoreCase("financial")) {
        text = "Renseignez uniquement les données du rapport financier.\n" +
               "Les données doivent correspondre au rapport des services prestés.\n" +
               "Le montant (prix unitaire * quantité) sera calculé automatiquement.\n\n" +
               "En cas de problème, contactez votre District de référence.";
    } else if (section.equalsIgnoreCase("stocks")) {
        text = "Renseignez uniquement les données du rapport de stock.\n" +
               "La quantité utilisé correspond aux données du rapport des services " +
               "prestés.\n" +
               "La quantité perdue correspond aux éléments qui n'ont pu être prestés.\n" +
               "La quantité reçue est celle reçue entre l'inventaire " +
               "(quantité initiale) et la fin du mois.\n" +
               "La quantité restante est calculée automatiquement.\n\n" +
               "En cas de problème, contactez votre District de référence.";
    } else if (section.equalsIgnoreCase("edit_number")) {
        text = "Changez le numéro du serveur uniquement sur demande expresse " +
               "de l'ANTIM.\n" +
               "Un mauvais numéro vous empêchera de transmettre vos rapports.\n\n" +
               "En cas de problème, contactez ANTIM.";
    } else if (section.equalsIgnoreCase("update_or_new")) {
        text = "Choisissez de reprendre un rapport si vous souhaitez que les " +
               "dernières données entrées dans le logiciel soient pré-remplies.\n" +
               "Cette possibilité vous permet de renvoyer facilement un " +
               "rapport dont l'envoi a échoué.\n" +
               "Cela vous permet aussi de continuer un rapport non terminé.\n" +
               "Si votre rapport a été correctement envoyé (confirmation du " +
               "serveur), créez un nouveau." +
               "En cas de problème, contactez ANTIM.";
    } else if (section.equalsIgnoreCase("send_report")) {
        text = "Renseignez votre identifiant, mot de passe et la période du " +
               "rapport.\n" +
               "Une fois le rapport envoyé, vous recevrez un SMS de confirmation " +
               "contenant un numéro de reçu.\n" +
               "Si vous ne recevez pas ce numéro rapidement, réessayer l'envoi.\n\n" +
               "En cas de problème, contactez ANTIM.";
    } else if (section.equalsIgnoreCase("request_help")) {
        text = "Cette fonction vous permet de contacter l'assistance technique.\n " +
               "Lorsque vous faites une demande d'aide, celle-ci est reçu par " +
               "l'ANTIM qui vous contactera dans des délais raisonnables. \n\n" +
               "Vous pouvez redemander de l'aide si personne ne vous contacte " +
               "dans les jours suivants.";
    } else if (section.equalsIgnoreCase("version")) {
        text = Constants.brand_name + " - Version " + Constants.version + "\n\n" +
               "En cas de problème, contactez ANTIM.";
    } else {
        text = "Aucune aide disponible pour cet élément.";
    }
    helpText = new StringItem(null, text);
}

    public void commandAction(Command c, Displayable d) {
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.returnTo);
        }
    }

}

