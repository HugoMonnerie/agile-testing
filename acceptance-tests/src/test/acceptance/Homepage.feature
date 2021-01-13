Feature: Fonctionnalités de ma page d'accueil
	Scenario: Vérification du titre et de la description
		Given je suis sur la homepage
		Then le titre doit être "Partagez vos passions | Meetup"
		And la description contient "Partagez vos passions et faites bouger votre ville"

	Scenario: Vérification de la punchline et la sous-punchline
		Given je suis sur la homepage
		Then la punchline doit être "Le monde vous tend les bras"
		And la sous-punchline contient "Rejoignez un groupe local pour rencontrer du monde, tester une nouvelle activité ou partager vos passions."

	Scenario: Vérification du bouton rouge
		Given je suis sur la homepage
		Then le bouton rouge doit avoir pour texte "Rejoindre Meetup"
		When le click sur le bouton rouge
		Then le bouton nous renvoie vers "https://secure.meetup.com/register/?_locale=fr-FR"
		
