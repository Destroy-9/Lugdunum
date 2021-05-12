package com.example.lugdunum;

public class Scenario {
    private int state;
    private String intro[] = {"Ave *user* ! En quelle année sommes-nous ? Ah, c'est marqué là : *date*\n *year* !? Déjà !?",
            "Pardon, tu ne dois pas comprendre ce que je raconte. Mon nom est *name*. Je suis né en l'an 700, en plein pendant le règne de Jules César. Je crois que vous nommez cette année \"53 avant J-C\". Je n'ai jamais compris pourquoi vous considérez que Jules César a règné 50 ans avant lui-même d'ailleurs... Mais peu importe.",
            "En l'an 711, des intempéries diluviennent s'abattirent sur la cité de Lugdunum durant plusieurs jours, et il arriva ce dont tout le monde avait peur à l'époque : le ciel nous tomba sur la tête. Lorsque celui-ci relâcha son étreinte sur la terre, beaucoup de personnes restèrent prisonniers des nuages, dont moi. Ce fut un moment terrible. Voir tous mes amis rester à terre quand moi je m'envolais... ",
            "Mais ce n'est pas tout, car lorsque les nuages s'évaporèrent, je me suis évaporé avec eux. Cela sonna le glas de mon existence physique. Je devins un âme errante entre ciel et terre. Depuis, je me matérialise régulièrement à travers tout ce que je peux. La dernière fois que c'est arrivé, c'était lors de la tempête de 1999, j'avais pu me condenser en nuage. ",
            "C'est la première fois que je retourne véritablement sur terre depuis ce qui est arrivé, grâce à ton application. Comme quoi, il y a du bon aussi dans les nouvelles technologies (même si les ondes perturbent mon cycle du sommeil).",
            "Bon, j'ai assez parlé ! J'ai besoin de toi pour me reconnecter à mon ancienne vie tant que je peux exister dans ton téléphone. Descend la rue du Trion, je veux voir si la fontaine est toujours là."
    };
    private String trion[] = {"Nous voilà devant la fontaine de Trion ! C'est marrant, elle ressemble vraiment aux fontaines qu'on trouvait à mon époque. Pourtant, elle n'a été construite qu'en 1836 pour alimenter le quartier en eau. C'est l'architecte René Dardel qui l'a construite (c'est aussi lui qui a créé la fontaine de la place Saint-Jean). ",
            "Je me rappelle de son sourire satisfait à la fin. J'avais pu capter un rayon de soleil qui m'avait projeté un instant contre la fontaine à son inauguration. J'y avais déjà fait un poc... oups. ",
            "Il y a un autre endroit un peu plus loin que j'ai découvert sur un coup de soleil. Si tu descends encore un tout petit peu, tu vas tomber sur un parking sur ta droite. Tout au fond de ce parking (attention, il est un peu long), il y a un portail qui ressemble à ça *photo*. Rends toi y, il y a un jardin derrière si mes souvenirs sont exacts. "
    };
    private String curiosites[] = {"Oh ! Tu as trouvé ! J'ai entendu dire en captant une onde radio en 2001 que ce jardin avait été offert par la ville de Montréal pour le 20 ème anniversaire des relations de coopération avec Lyon. Pour cette occasion, le sculpteur québecois Michel Goulet avait gravé 6 chaises.",
            "C'était très beau ce qu'il avait écrit dessus, peut-être pourrais-tu m'aider à retrouver ses gravures ? Je vois aussi que des bâtiments ont poussé dans le coin. J'ai un peu de mal à faire le lien entre ce que j'ai entendu et ce que je vois maintenant, peux-tu m'aider ?",
            "Merci pour m'avoir raffraichit la mémoire ! Si tu veux te poser un peu sur le chemin pour manger ou te reposer, c'est un bon spot ici. Sinon, on peut attendre le théâtre antique de Fourvière. Dis moi quand tu veux partir. "
    };
    private String fin[] = {"Je te souhaite bon vent, *user* !"};

    public Scenario() {
        state = 0;
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
            default: res = fin;
                    break;
        }

        state++;

        return res;
    }

    public int getState() {
        return state;
    }
}
