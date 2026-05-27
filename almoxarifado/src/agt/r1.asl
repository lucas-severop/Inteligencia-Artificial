viagens(5).

!start.


+!start : true 
    <- .print("guardo peças pequenas").

+peca(Tamanho) : Tamanho = peq 
    <- .print("percebi uma peça " , Tamanho, "e vou guarda-la");
        guardar(Tamanho).

+peca(Tamanho) : Tamanho = grd
    <- .print("percebi uma peça " , Tamanho, "e vou chamar r2 para me ajudar a guarda-la");
    .send(r2,achieve,vamosGuardar(Tamanho)).