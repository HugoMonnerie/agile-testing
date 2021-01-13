Feature: Fonctionnalité du configurateur.

    Scenario: Vérification du bouton commander
        Given je suis sur "https://www.tesla.com/fr_FR/models"
        When j'appuie sur le bouton Commander
        Then le bouton nous renvoie vers "https://www.tesla.com/fr_FR/models/design"

    Scenario: Vérification du prix LOA
        Given je suis sur "https://www.tesla.com/fr_FR/models/design"
        Then le prix affiché est un LOA à 768
        