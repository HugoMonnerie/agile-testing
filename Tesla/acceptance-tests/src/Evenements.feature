Feature: Fonctionnalité de la page d'évènements

    Scenario: Accéder aux évènements
        Given je suis sur "https://www.tesla.com/fr_FR"
        When j'ouvre le burger menu
        When je click sur le bouton "évènements"
        Then je me retrouve sur "https://www.tesla.com/fr_FR/events"

    Scenario: Les 15 prochains événements s'affichent
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        When je fais une recherche pour le lieu "Sartrouville, France"
        #When je choisi un lieu dans le monde
        Then La page contient les "15" prochains evenements autour de chez moi

    Scenario: Un lien permet de voir tout les événements
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        Then il ya un lien pour voit tout les évenements
        And il ya un lien pour voir plus de 15 évenements

    Scenario: Un formulaire ets disponible
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        Then je peut remplir un formulaire pour rester informé
        And il contient un champ "1" "Prénom"
        And il contient un champ "2" "Nom"
        And il contient un champ "3" "E-mail"
        And il contient un champ "4" "Téléphone"
        And il contient un champ "5" "Region"
        And il contient un champ "6" "Code Postal"
        And il contient un champ "7" "Recevoir les News Tesla"
        And il contient en bouton d'envoie "Suivant"

    Scenario: Je rempli le formulaire sauf E-mail
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        When je rempli tout les champs du formulaire sauf l'email
        And j'appuie sur Suivant
        Then un message textuel en rouge apparait sous le champ email indiquant "obligatoire"

    Scenario: Je recherche les événements au Japon
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        When je fais une recherche pour le lieu "Japon"
        And Je click sur le lien de l'inscription de cet évenement
        Then je me retrouve sur l'url' "https://auth.tesla.com/"

    Scenario: Recherche événement pour le Royaume Uni
        Given je suis sur "https://www.tesla.com/fr_FR/events"
        When je fais une recherche pour le lieu "Londres, Royaume-Uni"
        Then le premier évenement se situe au "Royaume-Uni"