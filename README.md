# FirstStock

	Initialisation du git

$ cd /path/of/project
$ git init
$ git remote add origin https://bitbucket.org/bisonflexible/language_theory.git

OU

$ cd /path/of/project
$ git clone https://bitbucket.org/bisonflexible/language_theory.git

Pull et Push du code

Au cas où on oublie...

PULL

$ git pull origin master

PUSH

$ cd /root/of/project
$ git add .
$ git commit -m "Add a message here"
$ git push origin master

	Pour configurer une base de donnée : 

Installer et configurer mysql
https://doc.ubuntu-fr.org/mysql

Installer et configurer phpmyadmin
https://doc.ubuntu-fr.org/phpmyadmin

Par la suite, pour lancer la base: 
sudo service mysql start

Lancer phpmyadmin: 
Dans le navigateur, entrer: http://localhost/phpmyadmin

Pour se connecter: de base:
root
mdp rentré dans la config de votre base mysql
