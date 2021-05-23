package com.example.lugdunum;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lugdunum.games.CuriosityGameActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class Scenario {
    private int state;
    private String intro[] = {
            "Ave *user* ! Tibi gratias ago pro tui invenire me. Quid ? Vos operor non intellego quid dicam ?",
            "Pardon, tu ne dois pas comprendre ce que je raconte. Heureusement, ta langue n'est pas si différente de la mienne. Mon nom complet est Julius Valerius Rhinus, mais utilise mon cognomen (Rhinus), c'est plus court.",
            "Je suis né en l'an 700, en plein pendant le règne de Jules César. Je crois que vous nommez cette année \"53 avant J-C\". Je n'ai jamais compris pourquoi vous considérez que Jules César a règné 50 ans avant lui-même d'ailleurs... Mais peu importe. ",
            "En l'an 711, des intempéries diluviennent s'abattirent sur la cité de Lugdunum durant plusieurs jours, et il arriva ce dont tout le monde avait peur à l'époque : le ciel nous tomba sur la tête. Lorsque celui-ci relâcha son étreinte sur la terre, beaucoup de personnes restèrent prisonnières des nuages, dont moi. Ce fut un moment terrible. Voir tous mes amis rester à terre quand moi je m'envolais... ",
            "Mais ce n'est pas tout, car lorsque les nuages s'évaporèrent, je me suis évaporé avec eux. Cela sonna le glas de mon existence physique. Je devins un âme errante entre ciel et terre.",
            "Depuis, je me matérialise régulièrement à travers tout ce que je peux. La dernière fois que c'est arrivé, c'était lors de la tempête de 1999, j'avais pu me condenser en nuage. ",
            "C'est la première fois que je retourne véritablement sur terre depuis ce qui est arrivé, grâce à ton application. Comme quoi, il y a du bon aussi dans les nouvelles technologies (même si les ondes perturbent mon cycle du sommeil). ",
            "Bon, j'ai assez parlé ! J'ai besoin de toi pour me reconnecter à mon ancienne vie tant que je peux exister dans ton téléphone. Descend la rue de Trion, je veux voir si la fontaine est toujours là. "
    };
    private String trion[] = {
            "Nous voilà devant la fontaine de Trion ! C'est marrant, elle ressemble vraiment aux fontaines qu'on trouvait à mon époque. Pourtant, elle n'a été construite qu'en 1836 pour alimenter le quartier en eau. C'est l'architecte René Dardel qui l'a construite (c'est aussi lui qui a créé la fontaine de la place Saint-Jean). ",
            "Je me rappelle de son sourire satisfait à la fin. J'avais pu capter un rayon de soleil qui m'avait projeté un instant contre la fontaine à son inauguration. J'y avais déjà fait un poc... oups. ",
            "Il y a un autre endroit un peu plus loin que j'ai découvert sur un coup de soleil. Si tu descends encore un tout petit peu, tu vas tomber sur un parking sur ta droite. Tout au fond de ce parking (attention, il est un peu long), il y a un portail qui ressemble à ça. Tu peux t'y rendre, il y a un jardin derrière si mes souvenirs sont exacts. "
    };
    private String curiosites[] = {
            "Oh ! Tu as trouvé ! J'ai entendu dire en captant une onde radio en 2001 que ce jardin avait été offert par la ville de Montréal pour le 20 ème anniversaire des relations de coopération avec Lyon. Pour cette occasion, le sculpteur québecois Michel Goulet avait gravé 6 chaises. ",
            "C'était très beau ce qu'il avait écrit dessus, peut-être pourrais-tu m'aider à retrouver ses gravures ? Je vois aussi que des bâtiments ont poussé dans le coin. J'ai un peu de mal à faire le lien entre ce que j'ai entendu et ce que je vois maintenant, peux-tu m'aider ?"
    };
    private String curiosites2[] = {
            "Merci de m'avoir rafraîchi la mémoire ! Si tu veux te poser un peu sur le chemin pour manger ou te reposer, c'est un bon spot ici. Sinon, on peut attendre le théâtre antique de Fourvière. Dis moi quand tu veux partir. "
    };
    private String curiosites_leave[] = {
            "Très bien, on s'en va. Le mieux est de retourner sur nos pas, et de reprendre notre route à la sortie du parking. À la sortie du parking, donc, on continue de descendre un peu, en se mettant sur le trottoir de gauche. ",
            "On va passer devant des résidences un peu oranges derrière lesquelles se trouvent des thermes romaines. Je me suis douché toute ma vie là dedans ! Tu peux aller voir si tu veux, mais ce n'est plus très intéressant aujourd'hui. ",
            "Ensuite, on passera devant le Lycée Saint-Just, ancien séminaire construit par Napoléon III et sous lequel se trouve la *Grotte Bérelle*, citerne gallo romaine de 440 m^3 qui constituait les réserves en eau de la ville. ",
            "Enfin, arrivés vers la place des minimes, on restera bien à gauche pour remonter en direction du théâtre ! On se retrouve là-bas. "
    };
    private String theatre1[] = {
            "Bienvenue dans mon chez moi ! C'est ici que je passais le plus clair de mon temps ! Je ne t'en ai pas parlé avant, mais à l'époque où j'avais un corps, j'étais comédien. Je formais un duo comique avec mon ami Cirus. On nous appelait \"Les RhinoCirus\". ",
            "Je suis très nostalgique de cette époque. Il y avait un endroit que j'appréciais particulièrement, saurais-tu le retrouver ? "
    };
    private String theatre2[] = {
            "Oui, je sais, c'est juste une scène. Mais ce que je voudrais te montrer c'est surtout la bouche d'égout au centre du cercle formé par la scène. Lorsque tu te trouves sur ce point, tous les sons que tu émets te reviennent en écho de façon uniforme. ",
            "Essaie de déclamer ce poème de Guy Créquie si tu t'y sens : "
    };
    private String theatre3[] = {
            "Ce théâtre est l'un des plus anciens du monde romain, et le plus ancien de Gaule. Il date de 15 avant J-C dans votre calendrier. À l'époque, il pouvait accueillir 10 000 spectateurs ! Même après des années de scène, j'avais toujours autant le trac avant de jouer ici. ",
            "De l'autre côté du site archéologique, tu peux trouver l'Odéon, qui était utilisé pour les représentations musicales, les lectures publiques, ou les récitations poétiques. Il est beaucoup plus petit que le Grand Théâtre, et beaucoup plus jeune. Il n'a que 3 000 places et n'a été construit qu'à la fin du 1er siècle après J-C. "
    };
    private String theatre4[] = {
            "Il y a aussi le prétoire du gouverneur de Lyon dans les parages, mais n'y vas pas, c'est dangereux les prétoires (c'est ce que vous appelez un palais de justice je crois maintenant). ",
            "Bon, je te laisse découvrir le site. Une fois que tu auras fait le tour, monte les marches et va en direction de la basilique de Fourvière, on se retrouve là-bas !"
    };
    private String fourviere1[] = {
            "Tu as trouvé la basilique ! Elle est chouette non ? Cet endroit était le forum Vetus à mon époque (d'où le nom de Fourvière). ",
            "Si mes souvenirs son exacts, les architectes Pierre Bossan et Louis Sainte-Marie Perrin ont construit la basilique par dessus entre 1873 et 1884 pour accueillir tous les pèlerins qui venaient de plus en plus nombreux pour le culte de la Vierge Marie. ",
            "Avant sa construction, seule la tour avec la vierge en or au dessus existait. Cette magnifique statue dorée fut d'ailleurs ajoutée le 8 décembre 1852, en souvenir des échevins qui avaient imploré Marie de les délivrer de la peste deux siècles plus tôt. ",
            "À cette occasion, tous les lyonnais allumèrent une bougie sur leur fenêtre. Ça te rappelle quelque chose ? Eh oui, la fête des lumières est née le 8 décembre 1852 ! ",
            "J'ai quelques souvenirs de statues présentes dans le coin, mais je n'arrive plus à me souvenir où, Peux-tu m'aider ?"
    };
    private String fourviere2[] = {
            "Merci à toi ! C'est marrant, je remarque que la Basilique n'est toujours pas beaucoup plus avancée que la dernière fois que je suis venu. Je ne sais pas si tu as fait attention, mais l'une des statues que tu as dû retrouver avait une forme assez rectangulaire. ",
            "Des blocs de pierre presque vierges comme celui-là, il y en a plein, mais faute de sculpteurs et de financements, ils ne sont pas terminés. Incroyable non ? ",
            "Je te laisse contempler l'éblouissant intérieur de la Basilique Notre-Dame de Fourvière si tu le souhaites, ainsi que la magnifique vue sur la ville de Lyon qui est disponible en allant vers l'esplanade. Dis moi quand tu veux partir !"
    };
    private String fourviere_leave[] = {
            "Super, allons-y ! Quand tu es sur l'esplanade, il te faut descendre les escaliers qui te mèneront au jardin du Rosaire. La balade est plutôt agréable tu verras. ",
            "En bas du jardin, quand tu passeras le portail, il te faudra traverser la route pour prendre les escaliers. Je te laisse me dire lorsque tu arrives en bas. "
    };
    private String traboule[] = {
            "Cher *user*, c'est ici que nos chemins se séparent. Je te remercie énormément de m'avoir permis de me balader à Lugdunum à hauteur d'homme ! Reviens quand tu veux, tu pourras m'y retrouver. ",
            "Rassures toi, je ne te quitte pas comme ça, perdu au milieu de la ville. Tu es ici dans la rue du Boeuf, dans laquelle se trouve une traboule au numéro 27. Pousse la porte, et traverse la. ",
            "En sortant de la traboule, tourne à droite dans la rue Saint-Jean. En continuant tout droit, tu traverseras la place Saint-Jean sur laquelle se trouve la Cathédrale, puis tu trouveras l'entrée du funiculaire.",
            "À la sortie de la traboule, je ne serai plus là. Je me serai évaporé dans les combles. "
    };
    private String fin[] = {"Je te souhaite bon vent, *user* !"};

    private int currentRhino;

    public Scenario() {

        state = 0;
        currentRhino = R.drawable.rhino1;
    }

    public String[] getHistory(){
        String res[];

        switch (state){
            case 0: res = intro;
                break;
            case 1: res = trion;
                break;
            case 2: res = curiosites;
                break;
            case 3: res = curiosites2;
                break;
            case 4: res = curiosites_leave;
                break;
            case 5: res = theatre1;
                break;
            case 6: res = theatre2;
                break;
            case 7: res = theatre3;
                break;
            case 8: res = theatre4;
                break;
            case 9: res = fourviere1;
                break;
            case 10: res = fourviere2;
                break;
            case 11: res = fourviere_leave;
                break;
            case 12: res = traboule;
                break;
            default: res = fin;
                break;
        }

        return res;
    }

    public int getContent(ImageView mImage, ImageView mPoem, Button mNextBtn){
        int res;

        switch (state) {
            case 0: mImage.setImageResource(R.drawable.fountain);
                res = 0;
                break;
            case 1: mImage.setImageResource(R.drawable.curiositeportal);
                res = 0;
                break;
            case 2: res = 1;
                break;
            case 3:
                res = 0;
                mNextBtn.setText("Partir");
                break;
            case 5: res = 0;
                mImage.setImageResource(R.drawable.theater);
                mNextBtn.setText("Trouvé !");
                break;
            case 6: res = 0;
                mPoem.setImageResource(R.drawable.poem);
                mImage.setImageResource(0);
                break;
            case 7: res = 0;
                mImage.setImageResource(R.drawable.odeon);
                break;
            case 9: res = 2;
                break;
            case 10:
                res = 0;
                mNextBtn.setText("Partir");
                break;
            case 11: res = 0;
                mNextBtn.setText("Je suis en bas !");
                break;
            case 12: res = 3;
                mNextBtn.setText("Finir");
                break;
            default: mImage.setImageResource(getCurrentRhino());
                res = 0;
                break;
        }

        return res;
    }

    public int getState() {
        return state;
    }

    public void incState(){
        state++;
    }

    public void decState(){
        state--;
    }

    public int getCurrentRhino() {
        if (state < 2){
            currentRhino = R.drawable.rhino1;
        }
        else if (state < 12){
            currentRhino = R.drawable.rhino2;
        }
        else{
            currentRhino = R.drawable.rhino3;
        }

        return currentRhino;
    }
}
