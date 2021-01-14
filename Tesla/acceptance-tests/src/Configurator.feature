#Feature: Fonctionnalité du configurateur.
#
#  Scenario: Vérification du bouton commander
#        Given je suis sur "https://www.tesla.com/fr_FR/models"
#        When j'appuie sur le bouton Commander
#        Then le bouton nous renvoie vers "https://www.tesla.com/fr_FR/models/design"
#
#    Scenario: Vérification du prix LOA
#        Given je suis sur "https://www.tesla.com/fr_FR/models/design"
#        Then le prix affiché est un "LOA" à "768 € /mois"
#
#    Scenario: Sélection des modèles
#        Given je suis sur "https://www.tesla.com/fr_FR/models/design"
#        When j'appuie sur "Grande Autonomie Plus"
#        Then le prix devient un "LOA" à "768 € /mois" et "108 € /mois" d'économies de carburant et un total de "94 841 €"
#        When j'appuie sur "Performance"
#        Then le prix devient un "LOA" à "969 € /mois" et "108 € /mois" d'économies de carburant et un total de "114 052 €"
#
#    Scenario:
#        Given je suis sur "https://www.tesla.com/fr_FR/models/design"
#        When je vais dans l'onglet "Pilotage Automatique"
#        When je click sur "Ajouter cette option"
#        Then Le prix augente de "89" € /mois
#
#    Scenario: Liste des localisations de vente
#        Given je suis sur "https://www.tesla.com/fr_fr/models/design#battery"
#        When j'appuie sur le logo
#        And je click sur le lien "Localisations"
#        Then je me retrouve sur la page "https://www.tesla.com/fr_FR/findus/list"
#
#    Scenario Outline:
#        Given je suis sur "https://www.tesla.com/fr_FR/model3"
#        When je vais dans les caractéristiques
#        And je choisis le modèle "<Gamme>"
#        Then le "Poids" est de "<Poids>"
#        And le "Accélération" est de "<Accélération>"
#        And le "Autonomie" est de "<autonomie>"
#
#    Examples:
#    | Gamme                 | Poids     | Accélération | autonomie  |
#    | Performance           | 1,844 kg  | 3,3 secondes | 567 km     |
#    | Grande Autonomie AWD  | 1,844 kg  | 4,4 secondes | 580 km     |
#    | Standard Plus         | 1,745 kg  | 5,6 secondes | 430 km     |
