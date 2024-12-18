document.addEventListener("DOMContentLoaded", () => {
  const editForm = document.querySelector(".editUserForm");
  const modal = document.getElementById("confirmModal");
  const confirmYes = document.getElementById("confirmYes");
  const confirmNo = document.getElementById("confirmNo");

  editForm.addEventListener("submit", function (event) {
    event.preventDefault();
    modal.style.display = "flex";

    confirmYes.onclick = () => {
      modal.style.display = "none";
      editForm.submit();
    };

    confirmNo.onclick = () => {
      modal.style.display = "none";
    };
  });
});
