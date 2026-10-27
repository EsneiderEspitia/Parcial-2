# Parcial-2
Solución Parcial 2

## Descripción del Proyecto
El sistema modela una jerarquía de criaturas donde una clase abstracta define las características y comportamientos 
base, y las subclases concretas implementan mecánicas de ataque y defensa únicas.

# Reglas de la simulación
Cada personaje obtendrá determinada cantidad de puntos de vida, en su método de ataque recibirá una cantidad de
puntos de danho aleatoria en un rango según su criatura, y en su método de defensa, recibirá un multiplicador 
para definir la cantidad de danho que recibe, las reglas son las siguientes:

Dragón: Posee 300 puntos de vida; su ataque estará en un rango de 10-25 puntos, con una efectividad que puede ir 
de x0.9 a x2.3; y su defensa estará entre x0.7 y x1.9

Mago: Posee 100 puntos de vida; su ataque estará en un rango de 1-15 puntos, con una efectividad que puede ir
de x1.3 a x4.5; y su defensa estará entre x0.9 y x1.4

Guerrero: Posee 150 puntos de vida; su ataque estará en un rango de 1-20 puntos, con una efectividad que puede ir
de x1.0 a x2.5; y su defensa estará entre x0.4 y x1.1

# Combate por turnos

1. El sistema asignará paralelamente y de manera aleatoria las variables de combate y defensa

2. El sistema asignará aleatoria y paralelamente un número entre 1 y 6 a cada criatura, la criatura que obtenga el
mayor número se asignará a la variable criatura1, y la otra en criatura2. En caso de empate, el sistema vuelve a 
asignar los números de manera aleatoria

3. El sistema se ejecutará en el siguiente orden:
      - criatura1 atacará >>> atqTotal = (atq * efectividad)
      - criatura 2 se defenderá >>> hp = hp - (criatura1.atacar * def)
      - validar vida criatura 2 >>> hp > 0
      - criatura 2 atacará >>> atqTotal = (atq * efectividad)
      - criatura 1 se defenderá >>> hp = hp - (criatura2.atacar * def)
      - validar vida criatura 1 >>> hp > 0
   Este ciclo se romperá solo cuando la validación de vida de alguna de las 2 criaturas sea negativa

# Funcionamiento de los métodos de las criaturas (cuando dice "criatura", se está autoreferenciando, si dice "criaturax", está referenciando al rival)

```
atacar(){
random atq (rango según la criatura)
random efectividad (rango según la criatura)
atqTotal = atq * efectividad
print criatura hizo atqTotal puntos de danho
return atqTotal
}

defender(){ 
random def (multiplicador según la criatura)
danhoR = criaturax.atacar * def
print criatura recibió danhoR puntos de danho
hp = hp - (criatura1.atacar * def)
print criatura quedó con hp puntos de vida
return hp
}

vida(){
return hp > 0
}

turno(){
t = random(1 - 6)
return t
}
```

# Funcionamiento "lógico" del Main

```
Main {
  Array donde se guardan las criaturas
  Ciclo que permite la impresión y la selección de las criaturas a simular
  do {a.turno
      b.turno} while (a.t === b.t)
  if (a.t > b.t) criatura1 = a
                 criatura2 = b
  else           criatura1 = a
                 criatura2 = b
  simularBatalla(criatura1, criatura2) {
    while (criatura1.vida y criatura2.vida) {criatura2.defender
                                            criatura1.defender}
  
  }  
}
```
