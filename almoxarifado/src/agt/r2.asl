viagens(3).

!start.

+!start : true 
    <- .print("guarda peças médias").

+peca(med) : true
    <- .print("percebi uma peça média e vou guarda-la");
        guardar(med).

+!vamosGuardar(grd)[source(Agt)] : true
    <- .print(Agt, "me chamou pra guardar peça grande");
        guardar(grd).