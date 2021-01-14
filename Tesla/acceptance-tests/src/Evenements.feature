Feature: Fonctionnalité de la page d'évènements

    Scenario: Accéder aux évènements
        Given je suis sur "https://www.tesla.com/fr_FR"
        When j'ouvre le burger menu
        When je click sur le bouton "évènements"
        Then je me retrouve sur "https://www.tesla.com/fr_FR/events"

