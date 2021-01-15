Feature: Fonctionnalités de ma page d'accueil
  Scenario: Vérification du titre
    Given je suis sur la homepage
    Then le titre doit être "Voitures électriques, énergie solaire et propre | Tesla France"

  Scenario: Vérification de la description
    Given je suis sur la homepage
    Then la description doit être "Tesla accélère la transition mondiale vers une énergie durable en proposant des véhicules électriques, des panneaux solaires et des solutions intégrées d'énergie renouvelable pour les particuliers et les entreprises."

  Scenario: Vérification des punchlines
    Given je suis sur la homepage
    Then la 1ère punchline doit être "Model 3"
    And la 2ème punchline doit être "Model S"
    And la 3ème punchline doit être "Model X"
    And la 4ème punchline doit être "Model Y"
    And la 5ème punchline doit être "Systèmes d'énergie solaire et Powerwalls"

  Scenario Outline: Vérification des liens
    Given je suis sur la homepage
    Then le titre est "<titre>"
    And le lien associé à "<titre>" est "<lien>"
    Examples:
      | titre     | lien                                  |
      | Model S   | https://www.tesla.com/fr_FR/models    |
      | Model 3   | https://www.tesla.com/fr_FR/model3    |
      | Model X   | https://www.tesla.com/fr_FR/modelx    |
      | Model Y   | https://www.tesla.com/fr_FR/modely    |
      | Powerwall | https://www.tesla.com/fr_fr/powerwall |
      | Recharger | https://www.tesla.com/fr_FR/charging  |

  Scenario: Vérification du burger menu
    Given je suis sur la homepage
    Then les noms des liens du burger menu doivent être "Véhicules disponibles", "Véhicules d'occasion", "Reprise", "Cybertruck", "Roadster", "Energie", "Essais", "Flottes et entreprises", "Nous trouver", "Evénements", "Assistance"

