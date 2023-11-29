% ALUNOS: GUSTAVO FURINI, LEONARDO NERVINO, HENRIQUE CONCEIÇÃO E PEDRO ARAUJO

% =-=-=-=-= EXERCICIO 1 =-=-=-=-=

pai(a,b).
pai(a,c).
pai(b,d).
pai(b,e).
pai(c,f).

% A)

irmao(X,Y):- pai(Z,X), pai(Z,Y).

% B)

primo(X,Y):- irmao(A,B), pai(A,X),  pai(B,Y).

% C)

neto(X,Y) :- pai(Y,B), pai(B, X).

% =-=-=-=-= EXERCICIO 2 =-=-=-=-=

quickSort([],[]):- !.
quickSort([Head|[Pivot|Tail]],Sorted):-
        split(Pivot,[Head|Tail],Less,Greater),
        quickSort(Less,SortedLess),
        quickSort(Greater,SortedGreater),
        append(SortedLess,[Pivot|SortedGreater],Sorted).
split(_,[],[],[]).
split(Pivot,[X|T],[X|Less],Greater):-
        X=<Pivot,split(Pivot,T,Less,Greater).
split(Pivot,[X|T],Less,[X|Greater]):-
        X>Pivot,split(Pivot,T,Less,Greater).


% =-=-=-=-= EXERCICIO 3 =-=-=-=-=

merge(List, List, []).
merge(List, [], List).

merge([MinList1|RestMerged], [MinList1|RestList1], [MinList2|RestList2]) :-
  MinList1 =< MinList2,
  merge(RestMerged,RestList1,[MinList2|RestList2]).
merge([MinList2|RestMerged], [MinList1|RestList1], [MinList2|RestList2]) :-
  MinList2 =< MinList1,
  merge(RestMerged,[MinList1|RestList1],RestList2).

mergeSort([], []).
mergeSort([A], [A|[]]).

mergeSort(Sorted, List) :-
    length(List, N),
    FirstLength is //(N, 2),
    SecondLength is N - FirstLength,
    length(FirstUnsorted, FirstLength),
    length(SecondUnsorted, SecondLength),
    append(FirstUnsorted, SecondUnsorted, List),
    mergeSort(FirstSorted, FirstUnsorted),
    mergeSort(SecondSorted, SecondUnsorted),
    merge(Sorted, FirstSorted, SecondSorted).
