package model;

public enum Especialidade {
    HARDWARE,
    SISTEMAS,
    REDES,
    ACESSOS
}

// A razão para separar é que Especialidade será um tipo próprio, que poderá ser utilizado por outras classes.

//Por exemplo, futuramente a classe Chamado também poderá trabalhar com essas especialidades para classificar problemas técnicos