wildfly alk. szerverrel:

1) Hozzd l�tre a k�vetkez� mapp�t ha nem l�tezne,

<Wildfly mappp�ja>/modules/system/layers/base/com/mysql/main

(a com alatt a mysql/main k�nyvt�ratakat kell l�trehozni ha ilyen m�g nem l�tezett volna kor�bban)

�s bem�solni a module.xml �s a mysql jconnector jar �llom�nyt.

2) inditsd ujra a wildfly-t (just in case)