
document.addEventListener("DOMContentLoaded", () => {
    const avatarImg = document.getElementById('avatar');
    const id = avatarImg.dataset.userId;

    fetch(`/api/v1/users/${id}/avatar`)
        .then(response =>  response.blob())
        .then(blob => {
            //document.getElementById('avatar').src = URL.createObjectURL(blob);
            avatarImg.src = URL.createObjectURL(blob);
        });
});

