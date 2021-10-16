% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% BASE DE CONOCIMIENTO DE LA APLICACION
% RONALD HERRERA GAMEZ
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

:- dynamic aula/3.
:- dynamic profesor/2.
:- dynamic curso/6.
:- dynamic imparte/2.
:- dynamic disponible/4.
:- dynamic disponibleA/4.




/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * CONSULTA 2:
 * Asociacion de profesores por semestre a cursos
 * tomando en cuenta el horario disponible del profesor.
 * P: almacena los horarios que se van ocupando de los profes
 * Hor: Lista a devolver con el horario
 */

consulta2([], _, Hor, X) :-
    not(Hor = []),
    % PARA EVITA QUE DE SOLUCIONES REPETIDAS
    % ESTO SUCEDE CUANDO SON CURSOS DE 2 CLASES POR SEMANA
    not(existe(Hor)),
    assert(historial(Hor)),
    reverse(Hor, X).
    %mostrarConsulta2(X).

% ---> PARA INSERTAR CURSOS DE 1 CLASE POR SEMANA
consulta2([H|T], P, Hor, X) :-
    % VALIDA QUE EL CURSO SEA DE 1 CLASE POR SEMANA
    curso(H,_,_,_,_, 1),
    imparte(H, IdProf),

    % BUSCA UN DIA LIBRE, YA SEA TODA LA MAÑANA O TARDE
    % EN LA QUE EL PROFESOR PUEDA DAR EL CURSO.
    % TAMBIEN VALIDA QUE NO SE HAYA TOMADO YA ESE HORARIO.
    hora(Ini, Med, Fin),
    disponible(IdProf, Dia, Ini,  Med),
    not(member(disponible(IdProf, Dia, Ini, _), P)),
    disponible(IdProf, Dia, Med,  Fin),
    not(member(disponible(IdProf, Dia, Med, _), P)),

    % VALIDA QUE NO HAYAN CHOQUES DE HORARIO CON OTROS CURSOS
    not(member([Dia, Ini, _, _, _], Hor)),

    % ALMACENA HORARIO DEL PROFESOR QUE HA SIDO OCUPADO
    P2 = [disponible(IdProf, Dia, Ini, Med),
             disponible(IdProf, Dia, Med, Fin)|P],
    % AÑADE AL HORARIO Y SIGUE BUSCANDO
    consulta2(T, P2,[[Dia, Ini, Fin, H, IdProf]|Hor], X).

% ---> PARA INSERTAR CURSOS DE 2 CLASES POR SEMANA
consulta2([H|T], P, Hor, X) :-
    % VALIDA QUE EL CURSO SEA DE 2 CLASES POR SEMANA
    curso(H,_,_,_,_, 2),
    imparte(H, IdProf),

    % BUSCA UN DIA QUE PUEDE DARLO EL PROFESOR
    % TAMBIEN VALIDA QUE NO SE HAYA TOMADO YA ESE HORARIO.
    hora(Ini, Med, _),
    disponible(IdProf, Dia, Ini,  Med),
    not(member(disponible(IdProf, Dia, Ini, _), P)),

    % VALIDA QUE NO HAYA CHOQUES EN ESE HORARIO CON LOS CURSOS
    not(member([Dia, Ini, _, _, _], Hor)),

    Hor2 = [[Dia, Ini, Med, H, IdProf]|Hor],
    P2 = [disponible(IdProf, Dia, Ini, Med)|P],

    % VALIDA QUE OTRO DIA PUEDE DARLO EL PROFESOR.
    hora(Ini2, Med2, _),
    disponible(IdProf, Dia2, Ini2,  Med2),
    % VALIDA QUE NO SEA EL MISMO DIA.
    not(Dia2 = Dia),
    % VALIDA QUE NO SE HAYA TOMADO ESE HORARIO DEL PROFESOR
    not(member(disponible(IdProf, Dia2, Ini2, _), P2)),

    % VALIDA QUE NO HAYA CHOQUE CON OTROS CURSOS
    not(member([Dia2, Ini2, _, _, _], Hor2)),

    consulta2(T, P2,[[Dia2,Ini2,Med2,H,IdProf]|Hor2], X).

% REGLA PARA LLAMADA - LIMPIA EL HISTORIAL
consulta2(NumSem, X) :-
    retractall(historial(_)),
    obtenerCursos(NumSem, LCur),
    consulta2(LCur, [], [], X).




/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * CONSULTA 4:
 * GENERA HORARIOS A PARTIR DE UNA LISTA DE CURSOS DADA.
 * MEDIANTE BACKTRAKING DEVUELVE LAS COMBINACIONES GENERADAS.
 * DETALLES DE LISTA: X = [['dia', 'idCur', ini, fin],...].
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

% PARA EL BACKTRAKING
dia('L'). dia('K'). dia('M'). dia('J'). dia('V').
hora(7, 9, 11).
hora(12, 14, 16).

% DADO UN DIA OBTIENE EL SIGUIENTE DEL SIGUIENTE
buscarSig('L', Horario, Hor, 'M') :-
    not(member(['M',_,Hor,_], Horario)).
buscarSig('K', Horario, Hor, 'J') :-
    not(member(['J',_,Hor,_], Horario)).
buscarSig('M', Horario, Hor, 'V') :-
    not(member(['V',_,Hor,_], Horario)).

gen([], Hor, X) :-
    not(Hor = []),
    reverse(Hor, X).

% ---> PARA INSERTAR CURSOS DE 1 CLASE POR SEMANA
gen([H|T], Hor, X) :-
    curso(H,_,_,_,_,1),
    dia(D),
    hora(Ini, _, Fin),
    not(member([D, _, Ini, _], Hor)), % EVITA CHOQUES DE HOR
    gen(T, [[D, H, Ini, Fin]|Hor], X).

% ---> PARA INSERTAR CURSOS DE 2 CLASES POR SEMANA
gen([H|T], Hor, X) :-
    curso(H,_,_,_,_,2),
    dia(Dia),
    hora(Ini, Med, _),
    % VALIDAMOS QUE NO HAYA COCHE DE HORARIO
    not(member([Dia, _, Ini,_], Hor)),
    % GUARDA EL NUEVO HORARIO EN LA LISTA
    Hor2 = [[Dia, H, Ini, Med]|Hor],
    % BUSCA QUE OTRO DIA PUEDE DARSE EL CURSO
    buscarSig(Dia, Hor2, Ini, Dia2),
    gen(T, [[Dia2, H, Ini, Med]|Hor2], X).

% METODO PARA LLAMADA
gen(Cursos, X):-
    gen(Cursos, [], X).




/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * CONSULTA 5:
 * GENERA HORARIOS A PARTIR DE LA DISPONIBILIDAD DE LOS
 * HORARIOS DE PROFESORES Y DE AULAS. MEDIANTE BACKTRAKING
 * P: almacena los horarios de los profes que se van ocupando
 * A: almacena los horarios de las aulas que se van ocupando
 * Hor: Lista a devolver con el horario
 * DEVUELVE LAS COMBINACIONES GENERADAS EN UNA LISTA:
 * L: X = [['dia', ini, fin, 'idCur', 'idProf','idAul'],...].
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

gen2([], _, _, Hor, X) :-
    not(Hor = []),
    reverse(Hor, X),
    % PARA EVITA QUE DE SOLUCIONES REPETIDAS
    % ESTO SUCEDE CUANDO SON CURSOS DE 2 CLASES POR SEMANA
    not(existe(X)),
    assert(historial(X)).
%    mostrarConsulta5(X).


% HORARIOS DE LA MAÑANA Y TARDE CURSOS DE 1 CLASE A LA SEMANA
gen2([H|T], P, A, Hor, X) :-
    % BUSCA EL CURSO Y QUE PROFESORES LO DAN
    curso(H,_,_,_, Tipo, 1),
    imparte(H, IdProf),

    % BUSCA QUE DIA PUEDE DARLO EL PROFESOR
    hora(Ini, Med, Fin),
    disponible(IdProf, Dia, Ini,  Med),
    not(member(disponible(IdProf, Dia, Ini, _), P)),
    disponible(IdProf, Dia, Med,  Fin),
    not(member(disponible(IdProf, Dia, Med, _), P)),

    % BUSCA AULA Y VALIDA QUE ESTE DISPONIBLE A ESA HORA
    aula(IdAul, Tipo, _),
    disponibleA(IdAul, Dia, Ini, Med),
    not(member(disponibleA(IdAul, Dia, Ini, _), A)),
    disponibleA(IdAul, Dia, Med, Fin),
    not(member(disponibleA(IdAul, Dia, Med, _), A)),

    % PARA QUE NO HAYAN CHOQUES DE HORARIO
    not(member([Dia, Ini,_,_,_,_], Hor)),
    % ALMACENA HORARIO DEL PROFESOR QUE HA SIDO OCUPADO
    P2 = [disponible(IdProf, Dia, Ini, Med),
             disponible(IdProf, Dia, Med, Fin)|P],
    % ALMACENA HORARIO DE LA AULA QUE HA SIDO OCUPADA
    A2 = [disponibleA(IdAul, Dia, Ini, Med),
             disponibleA(IdAul, Dia, Med, Fin)|A],
    % AÑADE AL HORARIO Y SIGUE BUSCANDO
    gen2(T,P2,A2,[[Dia, Ini, Fin, H, IdProf, IdAul]|Hor], X).

% HORARIOS DE LA MAÑANA Y TARDE CURSOS DE 2 CLASES A LA SEMANA
gen2([H|T], P, A, Hor, X) :-
    % BUSCA EL CURSO Y QUE PROFESORES LO DAN
    curso(H,_,_,_, Tipo, 2),
    imparte(H, IdProf),
    % BUSCA QUE DIA PUEDE DARLO EL PROFESOR
    hora(Ini, Med, _),
    disponible(IdProf, Dia, Ini,  Med),
    not(member(disponible(IdProf, Dia, Ini, _), P)),

    % VALIDA QUE NO HAYA CHOQUES EN ESE HORARIO.
    not(member([Dia, Ini, _, _, _, _], Hor)),

    Hor2 = [[Dia, Ini, Med, H, IdProf, IdAul]|Hor],
    P2 = [disponible(IdProf, Dia, Ini, Med)|P],

    % VALIDA QUE OTRO DIA PUEDE DARLO EL PROFESOR.
    hora(Ini2, Med2, _),
    disponible(IdProf, Dia2, Ini2,  Med2),
    % VALIDA QUE NO SEA EL MISMO DIA.
    not(Dia2 = Dia), % me bailo como 2000 combinaciones
    not(member(disponible(IdProf, Dia2, Ini2, _), P2)),

    % VALIDA QUE NO HAYA CHOQUE CON ESE OTRO HORARIO.
    not(member([Dia2, Ini2, _, _, _, _], Hor2)),

    % BUSCA AULA Y VALIDA QUE HAYA ESPACIO AMBOS DIAS.
    aula(IdAul, Tipo, _),

    disponibleA(IdAul, Dia, Ini, Med),
    not(member(disponibleA(IdAul, Dia, Ini, _), A)),

    A2 = [disponibleA(IdAul, Dia, Ini, Med)|A],

    disponibleA(IdAul, Dia2, Ini2, Med2),
    not(member(disponibleA(IdAul, Dia2, Ini2, _), A2)),

    gen2(T,P2,A2,[[Dia2,Ini2,Med2,H,IdProf,IdAul]|Hor2], X).

% METODO PARA LLAMADA
gen2(NumSem, X) :-
    retractall(historial(_)),
    obtenerCursos(NumSem, LCur),
    gen2(LCur, [], [], [], X).




/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * OTROS METODOS
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

% PARA EVITAR QUE NO SE REPITAN SOLUCIONES CUANDO HAY CURSOS
% QUE SON DE 2 CLASES POR SEMANA. SE USA EN CONSULTA 2 Y 5

:- dynamic historial/1.
miembro(X, E):-
    member(E, X).
existe(Sol) :-
    historial(X),
    maplist(miembro(X), Sol),!.

% CREA LISTA CON LOS IDS DE CURSOS DE UN SEMESTRE INDICADO
% UTILIZADA EN GEN2 (CONSULTA 2 Y 5)
obtenerCursos(NumSem, LCur) :-
    findall(Id, curso(Id,_,_, NumSem,_,_), LCur).

% PARA CONTAR EL TOTAL DE ELEMETOS DE CUALQUIER LISTA
contar_aux([], N, N).
contar_aux([_|T], N, Tot) :-
    N2 is N+1,
    contar_aux(T,N2,Tot).

% Mostrar el total de combinaciones de la consulta 2
total_2(Semestre, Tot) :-
    findall(X, consulta2(Semestre, X), L),
    contar_aux(L, 0, Tot).

% Mostrar el total de combinaciones de la consulta 4
total_gen(Cursos, Tot):-
    findall(X, gen(Cursos, X), L),
    contar_aux(L, 0, Tot).

% Mostrar el total de combinaciones de la consulta 5
total_gen2(Semestre, Tot):-
    findall(X, gen2(Semestre, X), L),
    contar_aux(L, 0, Tot).

% IMPRIMIR EL HORARIO GENERADOS EN LA CONSULTA 2
mostrarConsulta2([]):-
    write('____________________________________'),nl,nl.
mostrarConsulta2([[D, I, F, C, P]|T]):-
    curso(C,Nom,_,_,_,_), profesor(P, NomP),
    write('____________________________________'),nl,
    write(Nom),nl,
    write(NomP),nl,
    write(D),write(' '),write(I),write(' a '),write(F),nl,
    mostrarConsulta2(T).

% IMPRIMIR EL HORARIO GENERADOS EN LA CONSULTA 5
mostrarConsulta5([]):-
    write('____________________________________'),nl,nl.
mostrarConsulta5([[D, I, F, C, P, A]|T]):-
    curso(C,Nom,_,_,Tipo,CXS), profesor(P, NomP),
    write('____________________________________'),nl,
    write(Nom),write('  Tipo: '), write(Tipo),nl,
    write(NomP),nl,
    write(A),write(' Clases : '), write(CXS),nl,
    write(D),write(' '),write(I),write(' a '),write(F),nl,
    mostrarConsulta5(T).
	
	
