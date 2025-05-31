function updateBookingStatus(idBooking, trangThai) {
    fetch("/api/secure/bookings/update-status/?idBooking=" + idBooking + "&trangThai=" + trangThai, {
        method: "PATCH"
    })
    .then(res => {
        if (res.ok) {
            alert("Cập nhật trạng thái thành công");
            location.reload(); // Tải lại trang để cập nhật giao diện
        } else {
            alert("Cập nhật thất bại");
        }
    })
    .catch(err => {
        alert("Có lỗi xảy ra: " + err);
    });
}

function confirmCancel(el) {
    if (confirm("Bạn có chắc muốn hủy đặt chỗ này không?")) {
        const id = el.getAttribute("data-id");
        updateBookingStatus(id, "da_huy");
    }
    return false; // Ngăn chuyển trang
}