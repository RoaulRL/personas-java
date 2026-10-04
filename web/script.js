const formulario = document.getElementById("persona-form");
const mensaje = document.getElementById("mensaje");
const personasLista = document.getElementById("personas-lista");


// ======================================================
// MENSAJES
// ======================================================

function mostrarMensaje(texto) {

    mensaje.textContent = texto;

    setTimeout(function () {
        mensaje.textContent = "";
    }, 1000);
}


// ======================================================
// REGISTRAR PERSONA
// ======================================================

formulario.addEventListener("submit", async function (event) {

    event.preventDefault();

    const nombre =
        document.getElementById("nombre").value.trim();

    const apellido =
        document.getElementById("apellido").value.trim();

    const edad =
        Number(document.getElementById("edad").value);


    // Validación
    if (!nombre || !apellido || !edad) {

        mostrarMensaje(
            "Todos los campos son obligatorios."
        );

        return;
    }


    const persona = {
        nombre: nombre,
        apellido: apellido,
        edad: edad
    };


    try {

        const respuesta = await fetch("/personas", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(persona)

        });


        if (!respuesta.ok) {

            throw new Error(
                "No se pudo registrar la persona."
            );
        }


        await respuesta.json();


        mostrarMensaje(
            "Persona registrada correctamente."
        );


        formulario.reset();

        cargarPersonas();


    } catch (error) {

        console.error(error);

        mostrarMensaje(
            "Ocurrió un error al registrar la persona."
        );
    }
});


// ======================================================
// CARGAR PERSONAS
// ======================================================

async function cargarPersonas() {

    try {

        const respuesta = await fetch("/personas");


        if (!respuesta.ok) {

            throw new Error(
                "No se pudieron cargar las personas."
            );
        }


        const personas = await respuesta.json();


        personasLista.innerHTML = "";


        personas.forEach(function (persona) {

            const elemento =
                document.createElement("div");


            elemento.classList.add("persona");


            elemento.innerHTML = `

                <div>

                    <strong>
                        ${persona.nombre} ${persona.apellido}
                    </strong>

                    <span>
                        Edad: ${persona.edad}
                    </span>

                </div>


                <div>

                    <button
                        class="editar"
                        data-id="${persona.id}"
                        title="Editar persona"
                    >
                        ✏️
                    </button>


                    <button
                        class="eliminar"
                        data-id="${persona.id}"
                        title="Eliminar persona"
                    >
                        🗑️
                    </button>

                </div>

            `;


            // Botón eliminar
            const botonEliminar =
                elemento.querySelector(".eliminar");


            botonEliminar.addEventListener(
                "click",
                function () {

                    const id =
                        botonEliminar.dataset.id;

                    eliminarPersona(id);
                }
            );


            // Botón editar
            const botonEditar =
                elemento.querySelector(".editar");


            botonEditar.addEventListener(
                "click",
                function () {

                    editarPersona(
                        persona,
                        elemento
                    );
                }
            );


            personasLista.appendChild(elemento);

        });


    } catch (error) {

        console.error(error);

        mostrarMensaje(
            "No se pudieron cargar las personas."
        );
    }
}


// ======================================================
// ELIMINAR PERSONA
// ======================================================

async function eliminarPersona(id) {

    try {

        const respuesta =
            await fetch(`/personas/${id}`, {

                method: "DELETE"

            });


        if (!respuesta.ok) {

            throw new Error(
                "No se pudo eliminar la persona."
            );
        }


        await respuesta.text();


        mostrarMensaje(
            "Persona eliminada correctamente."
        );


        cargarPersonas();


    } catch (error) {

        console.error(error);

        mostrarMensaje(
            "No se pudo eliminar la persona."
        );
    }
}


// ======================================================
// EDITAR PERSONA
// ======================================================

async function editarPersona(persona, elemento) {


    elemento.innerHTML = `

        <div class="form-edicion">

            <input
                type="text"
                class="editar-nombre"
                value="${persona.nombre}"
            >


            <input
                type="text"
                class="editar-apellido"
                value="${persona.apellido}"
            >


            <input
                type="number"
                class="editar-edad"
                value="${persona.edad}"
                min="1"
            >

        </div>


        <div>

            <button
                class="guardar"
                title="Guardar cambios"
            >
                💾
            </button>


            <button
                class="cancelar"
                title="Cancelar edición"
            >
                ❌
            </button>

        </div>

    `;


    const botonGuardar =
        elemento.querySelector(".guardar");


    const botonCancelar =
        elemento.querySelector(".cancelar");


    // Cancelar edición
    botonCancelar.addEventListener(
        "click",
        function () {

            cargarPersonas();

        }
    );


    // Guardar cambios
    botonGuardar.addEventListener(
        "click",
        async function () {


            const nombre =
                elemento
                    .querySelector(".editar-nombre")
                    .value
                    .trim();


            const apellido =
                elemento
                    .querySelector(".editar-apellido")
                    .value
                    .trim();


            const edad =
                Number(
                    elemento
                        .querySelector(".editar-edad")
                        .value
                );


            // Validación
            if (!nombre || !apellido || !edad) {

                mostrarMensaje(
                    "Todos los campos son obligatorios."
                );

                return;
            }


            const personaActualizada = {

                nombre: nombre,

                apellido: apellido,

                edad: edad

            };


            try {

                const respuesta =
                    await fetch(
                        `/personas/${persona.id}`,
                        {

                            method: "PUT",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(
                                    personaActualizada
                                )

                        }
                    );


                if (!respuesta.ok) {

                    throw new Error(
                        "No se pudo actualizar la persona."
                    );
                }


                mostrarMensaje(
                    "Persona actualizada correctamente."
                );


                cargarPersonas();


            } catch (error) {

                console.error(error);

                mostrarMensaje(
                    "No se pudo actualizar la persona."
                );
            }

        }
    );
}


// ======================================================
// INICIAR APLICACIÓN
// ======================================================

cargarPersonas();
