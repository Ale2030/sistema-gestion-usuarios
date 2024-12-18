document.querySelectorAll(".delete").forEach((button) => {
  button.addEventListener("click", function () {
    const modal = document.getElementById("confirmModal");
    const confirmYes = document.getElementById("confirmYes");
    const confirmNo = document.getElementById("confirmNo");

    modal.style.display = "flex";

    confirmYes.onclick = async function () {
      modal.style.display = "none";
      const form = button.closest(".deleteUserForm");
      const formData = new FormData(form);

      try {
        const response = await fetch(form.action, {
          method: "POST",
          body: formData,
        });

        if (response.ok) {
          const message = await response.text();
          alert(message);
          window.location.reload();
        } else {
          const message = await response.text();
          alert(message);
        }
      } catch (error) {
        alert("Error al procesar la solicitud.");
      }
    };

    confirmNo.onclick = function () {
      modal.style.display = "none";
    };
  });
});
