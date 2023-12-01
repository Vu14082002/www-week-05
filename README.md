# WWW WEEK 05 MVC

## Candidates Endpoints

### Lấy danh sách tất cả ứng viên

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/candidates`
- **Mô tả:** Trả về danh sách tất cả các ứng viên trong hệ thống.

### Xem thông tin chi tiết của một ứng viên

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/candidates/{id}`
- **Mô tả:** Trả về thông tin chi tiết của một ứng viên dựa trên ID cụ thể.

### Thêm ứng viên mới

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/candidates/new`
- **Mô tả:** Trả về giao diện để thêm một ứng viên mới vào hệ thống.

### Cập nhật thông tin của ứng viên

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/candidates/update/{id}`
- **Mô tả:** Trả về giao diện để cập nhật thông tin của một ứng viên dựa trên ID cụ thể.

### Xóa ứng viên

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/candidates/delete/{id}`
- **Mô tả:** Xóa một ứng viên khỏi hệ thống dựa trên ID cụ thể.

### Lưu thông tin ứng viên

- **Method:** POST
- **Endpoint:** `http://localhost:8080/admin/candidates/save`
- **Mô tả:** Lưu thông tin của một ứng viên sau khi đã chỉnh sửa hoặc thêm mới.

## Companies Endpoints

### Lấy danh sách tất cả các công ty

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/companies`
- **Mô tả:** Trả về danh sách tất cả các công ty trong hệ thống.

### Xem thông tin chi tiết của một công ty

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/companies/{id}`
- **Mô tả:** Trả về thông tin chi tiết của một công ty dựa trên ID cụ thể.

### Thêm công ty mới

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/companies/new`
- **Mô tả:** Trả về giao diện để thêm một công ty mới vào hệ thống.

### Cập nhật thông tin của công ty

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/companies/update/{id}`
- **Mô tả:** Trả về giao diện để cập nhật thông tin của một công ty dựa trên ID cụ thể.

### Xóa công ty

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/companies/delete/{id}`
- **Mô tả:** Xóa một công ty khỏi hệ thống dựa trên ID cụ thể.

### Lưu thông tin công ty

- **Method:** POST
- **Endpoint:** `http://localhost:8080/admin/companies/save`
- **Mô tả:** Lưu thông tin của một công ty sau khi đã chỉnh sửa hoặc thêm mới.

## Jobs Endpoints

### Lấy danh sách tất cả các công việc

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/jobs`
- **Mô tả:** Trả về danh sách tất cả các công việc có trong hệ thống.

### Thêm công việc mới

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/jobs/new`
- **Mô tả:** Trả về giao diện để thêm một công việc mới vào hệ thống.

### Cập nhật thông tin của công việc

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/jobs/update/{id}`
- **Mô tả:** Trả về giao diện để cập nhật thông tin của một công việc dựa trên ID cụ thể.

### Xóa công việc

- **Method:** GET
- **Endpoint:** `http://localhost:8080/admin/jobs/delete/{id}`
- **Mô tả:** Xóa một công việc khỏi hệ thống dựa trên ID cụ thể.

### Lưu thông tin công việc

- **Method:** POST
- **Endpoint:** `http://localhost:8080/admin/jobs/save`
- **Mô tả:** Lưu thông tin của một công việc sau khi đã chỉnh sửa hoặc thêm mới.
