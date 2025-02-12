function openModal(title, contentUrl, isFullscreen) {
    const modal = document.createElement("div");
    modal.classList.add("modal", "fade", "transitionForce");
    modal.setAttribute("id", "UNIQUEMODAL");
    modal.setAttribute("tabindex", "-1");
    modal.setAttribute("role", "dialog");
    modal.setAttribute("aria-hidden", "true");
    modal.innerHTML = `
        <div class="  modal-dialog modal-dialog-centered modal-dialog-scrollable ${isFullscreen === true ? "modal-fullscreen" : ""} modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 id="UNIQUEMODAL-TITLE" class="modal-title"> Modale - ${title}</h4>
                </div>
                <div class="modal-body position-relative">
                    <iframe id="UNIQUEMODAL-IFRAME" src="${contentUrl}" class="BASECONTAINER ${isFullscreen === true ? "MODALFULLSCREENIFRAME" : "MODALIFRAME"} w-100 mt-3 "
                        style="height:10em;"
                        scrolling="auto">
                        </iframe>
                        
                    <button type="button" class="btn btn-primary position-absolute top-0 mt-3 end-0 translate-middle-x"
                        onclick="document.getElementById('UNIQUEMODAL-IFRAME').setAttribute('src', '${contentUrl}');">
                        <i class="fa-solid fa-rotate"></i>
                    </button>
                    </span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                    
                    <button type="button" class="hasToolbox btn btn-primary ${isFullscreen === true ? "d-none" : ""}"
                        data-bs-toggle="tooltip" data-bs-placement="top" data-bs-original-title="N'oubliez pas de valider vos actions/modifications avant !"
                        onclick="location.reload();">
                        Fermer et rafraîchir<br/>
                    </button>
                
                </div>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
    const modalInstance = new bootstrap.Modal(modal);
    modalInstance.show();
    modal.addEventListener("hidden.bs.modal", function () {
        document.body.removeChild(modal);
    });

    // Initialiser l'info-bulle sur le bouton "Fermer et rafraîchir"
    const refreshAndCloseBtn = modal.querySelector(".hasToolbox:last-child");
    if (refreshAndCloseBtn) {
        const tooltip = new bootstrap.Tooltip(refreshAndCloseBtn);
    }
}

const ERPMODALS = {
    open: {
        ajouter_document: function () {
            openModal("Reception de document", "modale_ajout_directionregionale.html", false);
        },

        remonter_document: function () {
            openModal("Reception de document", "modale_ajout_directionregionale.html", false);
        },

        diffuser_document: function (id) {
            openModal("Diffuser un document", "modale_communicationinterne.html?&id" + id, false);
        },

        // liste des documents diffusés à l'employé
        documents_employé: function (id) {
            openModal("Mes documents", "modale_communicationinterne.html?&id" + id, false);
        },

        creer_compte: function () {
            openModal("Création de compte", "modale_compte_creation.html", false);
        },

        modifier_compte: function (id) {
            openModal("Modification de compte", "modale_compte_modification.html?&id=" + id, false);
        },

        valider_compte: function () {
            openModal("Validation de compte", "modale_compte_validation.html", false);
        },

        creer_membre: function () {
            openModal("Création de membre", "modale_membre_creation.html", false);
        },

        modifier_membre: function (id) {
            openModal("Modification de membre", "modale_membre_modification.html?&id=" + id, false);
        },

        ajouter_formation: function () {
            openModal("Ajouter une formation", "modale_formation_creation.html", false);
        },

        modifier_formation: function (id) {
            openModal("Modifier une formation", "modale_formation_edit.html?&id=" + id, false);
        },

        creer_energie: function () {
            openModal("Création d'une énergie", "modale_energie_creation.html", false);
        },

        modifier_energie: function (id) {
            openModal("Modification d'une énergie'", "modale_energie_modification.html?&id=" + id, false);
        },

        creation_produit: function () {
            openModal("Création d'un produit", "modale_produit_creation.html", false);
        },

        modification_produit: function (id) {
            openModal("Modification d'un produit", "modale_produit_modification.html?&id=" + id, false);
        },

        creer_incident: function () {
            openModal("Nouvel incident", "modale_incident_creation.html", false);
        },

    },

    openPage: function (argTitle, argPage) {
        openModal(argTitle, argPage, true);
    },
};
