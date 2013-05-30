batallaPokemon
==============

Trabajo Práctico para la Materia Inteligencia Artificial - UP

Reglas de juego (UP Virtual - 9.0):

- Si al moverse en la celda destino esta el otro Warrior, no se puede avanzar mas, se queda en el casillero

- El primero a jugar es determinado por el azar de un numero ponderado por la velocidad del warrior.

- Se envia un lote de acciones en cada turno (3 acciones juntas).

- La fuerza del ataque es calculada con la fuerza del warrior modificada por la distribucion normal.

- Poner un Timeout al invocar al playTurn

- Definir zona de spawneo en las franjas verticales, en caso de que se muera uno, se resetean las posiciones iniciales.

- Al alcanzar un turno determinado se empieza a achicar el tamaño del mapa.

![Diagrama de Clases](https://raw.github.com/andrescabrera/Clinica/master/DiagramaClases.jpg)
