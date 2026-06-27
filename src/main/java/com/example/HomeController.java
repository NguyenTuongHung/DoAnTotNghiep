package com.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

    private final Map<String, List<Map<String, Object>>> categories = new LinkedHashMap<>();

    public HomeController() {
        categories.put("phong-khach", List.of(
                product("Sofa Wabi 2 Chỗ", "Sofa thư giãn Japandi, khung gỗ óc chó, đệm êm", "38.500.000₫", "Phòng Khách", "sofa-wabi-2-cho", "Linen Bỉ", "Khung gỗ óc chó", "Đệm cao su thiên nhiên", "Màu sage / nâu gỗ"),
                product("Sofa Wabi 3 Chỗ", "Sofa thư giãn Japandi, thiết kế sâu và thoải mái", "49.000.000₫", "Phòng Khách", "sofa-wabi-3-cho", "Vải linen", "Khung gỗ sồi", "Đệm hỗ trợ lưng", "Màu taupe"),
                product("Bàn Trà Kuro Tròn", "Bàn trà gỗ óc chó, mặt bàn tròn tối giản", "12.800.000₫", "Phòng Khách", "ban-tra-kuro", "Mặt bàn Ø90cm", "Chân chéo", "Gỗ óc chó đặc", "Phù hợp phòng khách nhỏ"),
                product("Đèn Sàn Iro", "Đèn sàn phong cách Nhật Bản, ánh sáng ấm áp", "9.600.000₫", "Phòng Khách", "den-san-iro", "Chiều cao 165cm", "Chao vải linen", "Nhiệt độ sáng 2700K", "Phù hợp góc đọc sách"),
                product("Ghế Lounge Yuki", "Ghế thư giãn bọc vải mềm và đường cong êm", "17.900.000₫", "Phòng Khách", "ghe-lounge-yuki", "Bọc vải cao cấp", "Khung gỗ sồi", "Tựa lưng cong", "Mẫu mới 2026"),
                product("Bàn Console Rin", "Bàn console thấp, phù hợp đặt dưới tường và trang trí", "15.200.000₫", "Phòng Khách", "ban-console-rin", "Gỗ walnut", "Mặt bàn mỏng", "Tay vịn đơn giản", "Phù hợp phòng nhỏ"),
                product("Kệ Tivi Hana", "Kệ tivi gỗ óc chó, tối giản và bền bỉ", "18.600.000₫", "Phòng Khách", "ke-tivi-hana", "Gỗ óc chó", "2 tầng mở", "Khung chắc chắn", "Tích hợp âm thanh"),
                product("Ghế Armchair Sumi", "Ghế đơn bọc vải nỉ, điểm nhấn màu đậm", "13.300.000₫", "Phòng Khách", "ghe-armchair-sumi", "Vải nỉ cao cấp", "Khung gỗ sồi", "Đệm ôm vừa phải", "Phù hợp góc đọc"),
                product("Tấm Thảm Kumo", "Thảm trải sàn len mềm, tạo cảm giác ấm áp", "8.900.000₫", "Phòng Khách", "tham-kumo", "Len dày", "Màu be trung tính", "Độ bền cao", "Phù hợp mọi phong cách"),
                product("Đèn Trần Nami", "Đèn trần hình vòng, ánh sáng mềm mại", "11.400.000₫", "Phòng Khách", "den-tran-nami", "Kim loại mạ màu", "Ánh sáng 3000K", "Thiết kế thấp", "Nhấn nhá cho phòng"),
                product("Ghế Xếp Ogi", "Ghế xếp gỗ và vải, tiện lợi cho khách", "6.500.000₫", "Phòng Khách", "ghe-xep-ogi", "Gỗ sồi", "Bọc vải linen", "Khả năng xếp gọn", "Dễ di chuyển")
        ));
        categories.put("phong-an", List.of(
                product("Bàn Ăn Hana", "Bàn ăn gỗ sồi, thiết kế thanh mảnh", "24.900.000₫", "Phòng Ăn", "ban-an-hana", "Mặt bàn 160cm", "Gỗ sồi nhập khẩu", "Chân vuông chắc chắn", "Phù hợp 6 người"),
                product("Ghế Ăn Mizu", "Ghế ăn bọc vải linen cao cấp", "7.200.000₫", "Phòng Ăn", "ghe-an-mizu", "Tựa lưng bọc vải", "Khung gỗ walnut", "Đệm mỏng êm", "Có sẵn 4 màu"),
                product("Kệ Sách Shizen 5 Tầng", "Kệ sách gỗ tần bì, tối giản và tinh tế", "12.800.000₫", "Phòng Ăn", "ke-sach-shizen", "5 tầng lưu trữ", "Gỗ tần bì", "Thiết kế linh hoạt", "H180cm"),
                product("Tủ Bếp Tono", "Tủ bếp thấp gọn, tối ưu không gian", "19.500.000₫", "Phòng Ăn", "tu-bep-tono", "3 ngăn kéo", "Mặt gỗ phủ dầu", "Bàn phím nhẹ", "Phù hợp bếp nhỏ"),
                product("Bàn Ăn Koto", "Bàn ăn gỗ sồi, mặt bàn dài vừa phải", "28.400.000₫", "Phòng Ăn", "ban-an-koto", "Mặt bàn 180cm", "Chân cong mềm", "Bền và hiện đại", "Phù hợp gia đình 4-6 người"),
                product("Ghế Ăn Nori", "Ghế ăn mảnh mai, bọc da giả cao cấp", "8.700.000₫", "Phòng Ăn", "ghe-an-nori", "Khung kim loại", "Đệm mút mềm", "Tựa lưng thấp", "Có 3 màu"),
                product("Tủ Rượu Aki", "Tủ rượu thấp gọn, phong cách Nhật Bản", "16.300.000₫", "Phòng Ăn", "tu-ruou-aki", "Gỗ sồi", "2 tầng", "Kính cường lực", "Phù hợp phòng ăn sang"),
                product("Kệ Đĩa Sora", "Kệ đĩa gỗ, dùng để trưng bày đồ dùng", "9.100.000₫", "Phòng Ăn", "ke-dia-sora", "Gỗ walnut", "3 tầng", "Thiết kế thanh mảnh", "Tối ưu không gian"),
                product("Đèn Chùm Yori", "Đèn chùm nhỏ, ánh sáng ấm áp cho bàn ăn", "10.800.000₫", "Phòng Ăn", "den-chum-yori", "Kim loại ánh bạc", "Ánh sáng 2700K", "Thiết kế hài hòa", "Phù hợp bàn ăn trung bình"),
                product("Bộ Ghế Bar Fudo", "Bộ ghế bar cho góc ăn uống", "17.900.000₫", "Phòng Ăn", "bo-ghe-bar-fudo", "Gỗ sồi", "Tựa lưng cong", "Thép phủ sơn", "Phù hợp bếp mở")
        ));
        categories.put("phong-ngu", List.of(
                product("Giường Shoji", "Giường gỗ sồi, đầu giường tối giản", "31.500.000₫", "Phòng Ngủ", "giuong-shoji", "Kích thước Queen", "Đầu giường gỗ", "Tủ ngăn dưới giường", "Mặt buộc chắc chắn"),
                product("Tủ Quần Áo Riku", "Tủ áo thấp gọn, nhiều ngăn lưu trữ", "29.000.000₫", "Phòng Ngủ", "tu-quan-ao-riku", "3 cánh cửa", "Ngăn kéo 4 tầng", "Gỗ walnut", "Kết hợp đèn LED"),
                product("Bàn Trang Điểm Aoi", "Bàn trang điểm nhỏ gọn, ánh sáng dịu", "13.400.000₫", "Phòng Ngủ", "ban-trang-diem-aoi", "Kích thước 90x45cm", "Gương soi lớn", "Ngăn kéo nhỏ", "Phù hợp phòng ngủ tiện nghi"),
                product("Đầu Giường Maki", "Đầu giường gỗ cao cấp, thiết kế hiện đại", "14.800.000₫", "Phòng Ngủ", "dau-giuong-maki", "Gỗ óc chó", "Mặt phẳng", "Độ bền cao", "Phù hợp giường king"),
                product("Tủ Đựng Đồ Kura", "Tủ đồ đa năng, nhiều ngăn lưu trữ", "18.200.000₫", "Phòng Ngủ", "tu-dung-do-kura", "Gỗ walnut", "4 ngăn kéo", "Tay nắm gỗ", "Tiết kiệm diện tích"),
                product("Bàn Cạnh Giường Tsuba", "Bàn nhỏ nghiêng, tiện dụng cho đồ cá nhân", "5.900.000₫", "Phòng Ngủ", "ban-canh-giuong-tsuba", "Gỗ sồi", "Mặt bàn hẹp", "Thiết kế nhẹ", "Phù hợp phòng nhỏ"),
                product("Đèn Cố Định Neko", "Đèn ngủ nhỏ, ánh sáng dịu và ấm", "4.300.000₫", "Phòng Ngủ", "den-co-dinh-neko", "Kim loại phủ sơn", "Ánh sáng 2400K", "Chất liệu bền", "Tạo cảm giác thư giãn"),
                product("Ghế Ngồi Gỗ Hina", "Ghế ngồi bọc vải mềm cho góc thư giãn", "9.400.000₫", "Phòng Ngủ", "ghe-ngoi-go-hina", "Khung gỗ", "Đệm mút", "Nhẹ và tiện", "Phù hợp phòng ngủ"),
                product("Kệ Đựng Sách Tora", "Kệ sách nhỏ, phù hợp góc đọc", "7.800.000₫", "Phòng Ngủ", "ke-dung-sach-tora", "Gỗ tần bì", "2 tầng", "Thiết kế tối giản", "Phù hợp phòng ngủ"),
                product("Tủ Áo Mini Riko", "Tủ áo mini cho phòng ngủ nhỏ", "21.500.000₫", "Phòng Ngủ", "tu-ao-mini-riko", "Gỗ sồi", "1 cánh", "Lưu trữ tối đa", "Phù hợp phòng 10m2")
        ));
        categories.put("phu-kien", List.of(
                product("Gương Hikari", "Gương tròn viền gỗ, ánh sáng dịu", "6.800.000₫", "Phụ Kiện", "guong-hikari", "Đường kính 70cm", "Viền gỗ sồi", "Độ phản chiếu cao", "Phù hợp phòng ngủ"),
                product("Vase Aki", "Vase thủy tinh mờ, màu trung tính", "3.500.000₫", "Phụ Kiện", "vase-aki", "Thủy tinh mờ", "Màu beige", "Thiết kế hiện đại", "Dùng cho hoa tươi"),
                product("Đèn Bàn Kumo", "Đèn bàn nhỏ gọn, ánh sáng ấm áp", "4.900.000₫", "Phụ Kiện", "den-ban-kumo", "Chiều cao 38cm", "Chân đồng", "Ánh sáng 3000K", "Phù hợp bàn làm việc"),
                product("Hộp Đựng Đồ Yui", "Hộp đựng đồ bằng gỗ, sang trọng và tiện lợi", "2.900.000₫", "Phụ Kiện", "hop-dung-do-yui", "Gỗ mịn", "Bốn góc bo", "Dễ dùng", "Phù hợp tủ quần áo"),
                product("Tượng Trang Trí Momo", "Tượng decor nhỏ, tạo điểm nhấn cho góc nhìn", "1.800.000₫", "Phụ Kiện", "tuong-trang-tri-momo", "Gốm men", "Màu nâu đất", "Thiết kế đơn giản", "Phù hợp bàn trà"),
                product("Giỏ Tre Iro", "Giỏ tre đựng đồ, vừa đẹp vừa hữu ích", "1.600.000₫", "Phụ Kiện", "gio-tre-iro", "Tre tự nhiên", "Đường may chắc", "Màu tự nhiên", "Phù hợp phòng khách"),
                product("Đèn Ngủ Riku", "Đèn ngủ nhỏ gọn, ánh sáng dịu", "3.200.000₫", "Phụ Kiện", "den-ngu-riku", "Kim loại phủ sơn", "Nhiệt độ sáng 2700K", "Thiết kế thấp", "Phù hợp bàn đầu giường"),
                product("Khung Tranh Koto", "Khung tranh gỗ, mang vẻ đẹp tối giản", "2.100.000₫", "Phụ Kiện", "khung-tranh-koto", "Gỗ walnut", "Màu nâu ấm", "Phù hợp trưng bày", "Kích thước 60x80"),
                product("Bình Hoa Aoi", "Bình hoa thủy tinh, đặt trong phòng khách", "2.700.000₫", "Phụ Kiện", "binh-hoa-aoi", "Thủy tinh trong", "Màu xám nhạt", "Dễ vệ sinh", "Phù hợp decor"),
                product("Móc Tre Sumi", "Móc tre treo áo và túi, đẹp và tiện lợi", "1.200.000₫", "Phụ Kiện", "moc-tre-sumi", "Tre tự nhiên", "3 móc", "Màu nâu", "Phù hợp phòng ngủ")
        ));
        categories.put("van-phong", List.of(
                product("Bàn Làm Việc Umi", "Bàn làm việc gỗ óc chó, mặt bàn rộng", "19.900.000₫", "Văn Phòng", "ban-lam-viec-umi", "Mặt bàn 140x70cm", "Khung gỗ chắc chắn", "Hộc lưu trữ", "Phù hợp làm việc tại nhà"),
                product("Ghế Làm Việc Sora", "Ghế công thái học có đệm êm", "14.700.000₫", "Văn Phòng", "ghe-lam-viec-sora", "Điều chỉnh ngả lưng", "Đệm lưng hỗ trợ", "Chân đế bền", "Tương thích văn phòng"),
                product("Kệ Máy Tính Rin", "Kệ máy tính gọn nhẹ, tối giản", "11.200.000₫", "Văn Phòng", "ke-may-tinh-rin", "2 tầng", "Lưu trữ ngăn kéo", "Thiết kế hẹp", "Phù hợp góc làm việc"),
                product("Bàn Học Koa", "Bàn học nhỏ gọn cho học sinh và freelancer", "12.400.000₫", "Văn Phòng", "ban-hoc-koa", "Mặt bàn gỗ", "Sức chứa nhẹ", "Thiết kế tiết kiệm", "Phù hợp không gian hẹp"),
                product("Ghế Tư Vấn Nao", "Ghế thư giãn cho phòng làm việc", "10.600.000₫", "Văn Phòng", "ghe-tu-van-nao", "Bọc da giả", "Khung sắt", "Tựa lưng thoải mái", "Phù hợp phòng họp"),
                product("Tủ Hồ Sơ Miki", "Tủ hồ sơ gỗ 3 ngăn, sang trọng", "15.100.000₫", "Văn Phòng", "tu-ho-so-miki", "Gỗ walnut", "3 ngăn", "Khay kéo", "Phù hợp văn phòng nhỏ"),
                product("Kệ Sách Duo", "Kệ sách 2 tầng cho góc làm việc", "7.900.000₫", "Văn Phòng", "ke-sach-duo", "Gỗ tần bì", "2 tầng", "Thiết kế mở", "Phù hợp tủ nhỏ"),
                product("Đèn bàn Toma", "Đèn bàn công thái học, ánh sáng tốt", "5.300.000₫", "Văn Phòng", "den-ban-toma", "Kim loại", "Ánh sáng trắng", "Chỉnh góc được", "Phù hợp bàn làm việc"),
                product("Giá Treo Laptop Ryo", "Giá treo laptop tiện dụng", "2.600.000₫", "Văn Phòng", "gia-treo-laptop-ryo", "Gỗ và nhựa", "Đế đứng", "Dễ lắp", "Phù hợp bàn làm việc"),
                product("Bộ Kệ Tài Liệu Seki", "Bộ kệ tài liệu cho góc làm việc", "13.800.000₫", "Văn Phòng", "bo-ke-tai-lieu-seki", "Gỗ óc chó", "3 tầng", "Cố định chắc chắn", "Tối ưu lưu trữ")
        ));
        categories.put("khong-gian-ca-nhan", List.of(
                product("Bộ Trang Trí Wabi", "Bộ sản phẩm phối hợp cho không gian cá nhân", "34.000.000₫", "Không Gian Cá Nhân", "bo-trang-tri-wabi", "Bao gồm 3 món", "Đồng bộ phong cách", "Phù hợp phòng khách", "Tặng kèm phụ kiện nhỏ"),
                product("Bộ Đèn Tạo Cảm Hứng", "Bộ đèn và vật trang trí cho góc thư giãn", "16.800.000₫", "Không Gian Cá Nhân", "bo-den-tao-cam-hung", "2 đèn bàn", "Vase decor", "Màu ấm", "Tạo cảm giác riêng"),
                product("Bộ Góc Đọc Sora", "Bộ sản phẩm cho góc đọc cá nhân", "22.400.000₫", "Không Gian Cá Nhân", "bo-goc-doc-sora", "Ghế + kệ + đèn", "Tạo cảm giác riêng", "Phù hợp không gian nhỏ", "Màu nâu ấm"),
                product("Bộ Spa Ngủ Mizu", "Bộ sản phẩm thư giãn cho phòng ngủ", "20.700.000₫", "Không Gian Cá Nhân", "bo-spa-ngu-mizu", "Đèn + gương + hộp", "Thiết kế nhẹ", "Phù hợp phòng ngủ", "Mang lại cảm giác thư thái"),
                product("Bộ Tạo Năng Lượng Kumo", "Bộ phụ kiện cho góc làm việc cá nhân", "18.900.000₫", "Không Gian Cá Nhân", "bo-tao-nang-luong-kumo", "Bàn + đèn + hộp", "Tối ưu tập trung", "Kết hợp gỗ", "Phù hợp tại nhà"),
                product("Bộ Sắc Nâu Ryo", "Bộ sản phẩm phối hợp các món nâu ấm", "25.600.000₫", "Không Gian Cá Nhân", "bo-sac-nau-ryo", "3 món", "Tôn lên vẻ ấm cúng", "Dễ phối hợp", "Cá tính nhưng vẫn thanh lịch"),
                product("Bộ Tinh Tế Hina", "Bộ đồ dùng cho không gian sống hiện đại", "19.300.000₫", "Không Gian Cá Nhân", "bo-tinh-te-hina", "2 món chính", "Đời sống riêng", "Thiết kế nhẹ", "Phù hợp căn hộ"),
                product("Bộ Ban Công Nami", "Bộ phụ kiện cho góc ban công", "17.100.000₫", "Không Gian Cá Nhân", "bo-ban-cong-nami", "Ghế + bàn nhỏ", "Không gian thư giãn", "Dễ bảo trì", "Phù hợp ngoại thất"),
                product("Bộ Gần Gũi Koto", "Bộ sản phẩm mang cảm giác gần gũi", "21.800.000₫", "Không Gian Cá Nhân", "bo-gan-gui-koto", "Đèn + thảm + hộp", "Cảm giác ấm", "Dễ phối màu", "Cho từng phong cách"),
                product("Bộ Tạo Điểm Nhấn Aki", "Bộ decor mang lại sự nổi bật", "14.500.000₫", "Không Gian Cá Nhân", "bo-tao-diem-nhan-aki", "3 phụ kiện", "Dễ đặt", "Phù hợp mọi góc", "Tạo dấu ấn cá nhân")
        ));
    }

    private Map<String, Object> product(String name, String description, String price, String category, String slug, String material, String feature1, String feature2, String feature3) {
        Map<String, Object> item = new LinkedHashMap<>();
        item.put("name", name);
        item.put("description", description);
        item.put("price", price);
        item.put("category", category);
        item.put("slug", slug);
        item.put("material", material);
        item.put("feature1", feature1);
        item.put("feature2", feature2);
        item.put("feature3", feature3);
        return item;
    }

    private String categoryNameForSlug(String slug) {
        return switch (slug) {
            case "phong-khach" -> "Phòng Khách";
            case "phong-an" -> "Phòng Ăn";
            case "phong-ngu" -> "Phòng Ngủ";
            case "phu-kien" -> "Phụ Kiện";
            case "van-phong" -> "Văn Phòng";
            case "khong-gian-ca-nhan" -> "Không Gian Cá Nhân";
            default -> "Tất Cả Sản Phẩm";
        };
    }

    private String categoryDescriptionForSlug(String slug) {
        return switch (slug) {
            case "phong-khach" -> "Sofa, ghế thư giãn, bàn trà và phụ kiện sang trọng.";
            case "phong-an" -> "Bàn ăn, ghế ăn, tủ và kệ lưu trữ tối giản.";
            case "phong-ngu" -> "Giường, tủ đầu giường, bàn trang điểm và lưu trữ.";
            case "phu-kien" -> "Đèn sàn, đèn bàn, gương, vase và vật dụng decor.";
            case "van-phong" -> "Bàn làm việc, ghế công thái học và kệ sách hiện đại.";
            case "khong-gian-ca-nhan" -> "Các bộ sản phẩm phối hợp cho từng phong cách sống.";
            default -> "Khám phá các sản phẩm được chọn riêng cho không gian của bạn.";
        };
    }

    private String categoryPillForSlug(String slug) {
        return switch (slug) {
            case "phong-khach" -> "Sofa & Ghế";
            case "phong-an" -> "Bàn & Kệ";
            case "phong-ngu" -> "Giường & Tủ";
            case "phu-kien" -> "Đèn & Trang trí";
            case "van-phong" -> "Làm việc";
            case "khong-gian-ca-nhan" -> "Bộ sưu tập";
            default -> "Sản phẩm";
        };
    }

    @GetMapping({"/", "/index"})
    public String home(Model model) {
        List<Map<String, Object>> categorySummaries = new ArrayList<>();
        for (Map.Entry<String, List<Map<String, Object>>> entry : categories.entrySet()) {
            Map<String, Object> summary = new LinkedHashMap<>();
            String slug = entry.getKey();
            summary.put("slug", slug);
            summary.put("name", categoryNameForSlug(slug));
            summary.put("description", categoryDescriptionForSlug(slug));
            summary.put("pill", categoryPillForSlug(slug));
            summary.put("count", entry.getValue().size());
            categorySummaries.add(summary);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("categorySummaries", categorySummaries);
        return "index";
    }

    private boolean matchesQuery(Map<String, Object> product, String query) {
        if (query == null || query.isBlank()) {
            return true;
        }
        String normalizedQuery = query.trim().toLowerCase(Locale.ROOT);
        String searchableText = String.join(" ",
                String.valueOf(product.get("name")),
                String.valueOf(product.get("description")),
                String.valueOf(product.get("material")),
                String.valueOf(product.get("feature1")),
                String.valueOf(product.get("feature2")),
                String.valueOf(product.get("feature3"))
        ).toLowerCase(Locale.ROOT);
        return searchableText.contains(normalizedQuery);
    }

    private boolean matchesKind(Map<String, Object> product, String kind) {
        if (kind == null || kind.isBlank()) {
            return true;
        }
        String normalizedKind = kind.trim().toLowerCase(Locale.ROOT);
        String searchableText = String.join(" ",
                String.valueOf(product.get("name")),
                String.valueOf(product.get("description"))
        ).toLowerCase(Locale.ROOT);
        return searchableText.contains(normalizedKind);
    }

    private boolean matchesMaterial(Map<String, Object> product, String material) {
        if (material == null || material.isBlank()) {
            return true;
        }
        String normalizedMaterial = material.trim().toLowerCase(Locale.ROOT);
        String actualMaterial = String.valueOf(product.get("material")).toLowerCase(Locale.ROOT);
        return actualMaterial.contains(normalizedMaterial);
    }

    private boolean matchesPriceRange(Map<String, Object> product, String minPrice, String maxPrice) {
        if ((minPrice == null || minPrice.isBlank()) && (maxPrice == null || maxPrice.isBlank())) {
            return true;
        }
        long price = parsePrice(String.valueOf(product.get("price")));
        Long minimum = (minPrice == null || minPrice.isBlank()) ? null : Long.parseLong(minPrice);
        Long maximum = (maxPrice == null || maxPrice.isBlank()) ? null : Long.parseLong(maxPrice);

        if (minimum != null && price < minimum) {
            return false;
        }
        if (maximum != null && price > maximum) {
            return false;
        }
        return true;
    }

    private long parsePrice(String priceText) {
        String digitsOnly = priceText.replaceAll("[^0-9]", "");
        if (digitsOnly.isBlank()) {
            return 0;
        }
        return Long.parseLong(digitsOnly);
    }

    @GetMapping("/products/{slug}")
    public String products(@PathVariable String slug,
                            @RequestParam(required = false) String q,
                            @RequestParam(required = false) String kind,
                            @RequestParam(required = false) String material,
                            @RequestParam(required = false) String minPrice,
                            @RequestParam(required = false) String maxPrice,
                            Model model) {
        List<Map<String, Object>> allProducts = categories.getOrDefault(slug, List.of());
        List<Map<String, Object>> products = new ArrayList<>();
        for (Map<String, Object> product : allProducts) {
            if (matchesQuery(product, q)
                    && matchesKind(product, kind)
                    && matchesMaterial(product, material)
                    && matchesPriceRange(product, minPrice, maxPrice)) {
                products.add(product);
            }
        }

        String categoryName = categoryNameForSlug(slug);
        model.addAttribute("categorySlug", slug);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryDescription", categoryDescriptionForSlug(slug));
        model.addAttribute("productCount", products.size());
        model.addAttribute("searchQuery", q == null ? "" : q.trim());
        model.addAttribute("selectedKind", kind == null ? "" : kind.trim());
        model.addAttribute("selectedMaterial", material == null ? "" : material.trim());
        model.addAttribute("minPrice", minPrice == null ? "" : minPrice.trim());
        model.addAttribute("maxPrice", maxPrice == null ? "" : maxPrice.trim());
        model.addAttribute("backUrl", "/");
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{slug}/suggest")
    @ResponseBody
    public ResponseEntity<List<String>> suggest(@PathVariable String slug,
                                                 @RequestParam(required = false) String q) {
        List<Map<String, Object>> allProducts = categories.getOrDefault(slug, List.of());
        List<String> suggestions = new ArrayList<>();
        String normalizedQuery = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);

        if (!normalizedQuery.isBlank()) {
            for (Map<String, Object> product : allProducts) {
                String searchableText = String.join(" ",
                        String.valueOf(product.get("name")),
                        String.valueOf(product.get("description")),
                        String.valueOf(product.get("material"))
                ).toLowerCase(Locale.ROOT);
                if (searchableText.contains(normalizedQuery)) {
                    suggestions.add(String.valueOf(product.get("name")));
                }
                if (suggestions.size() >= 6) {
                    break;
                }
            }
        }
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/product/{slug}")
    public String productDetail(@PathVariable String slug, Model model) {
        for (Map.Entry<String, List<Map<String, Object>>> entry : categories.entrySet()) {
            for (Map<String, Object> product : entry.getValue()) {
                if (slug.equals(product.get("slug"))) {
                    String categorySlug = entry.getKey();
                    model.addAttribute("product", product);
                    model.addAttribute("categoryName", categoryNameForSlug(categorySlug));
                    model.addAttribute("categoryDescription", categoryDescriptionForSlug(categorySlug));
                    model.addAttribute("productCount", entry.getValue().size());
                    model.addAttribute("backUrl", "/products/" + categorySlug);
                    return "product-detail";
                }
            }
        }
        return "redirect:/";
    }

    public String addToCart(String slug, int quantity, HttpSession session) {
        return addToCart(slug, quantity, false, session);
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String slug,
                            @RequestParam(defaultValue = "1") int quantity,
                            @RequestParam(defaultValue = "false") boolean checkout,
                            HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        for (int i = 0; i < Math.max(1, quantity); i++) {
            cart.add(slug);
        }
        session.setAttribute("cart", cart);
        if (checkout) {
            return "redirect:/cart";
        }
        return "redirect:/product/" + slug;
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        List<Map<String, Object>> cartItems = new ArrayList<>();
        for (String slug : cart) {
            for (List<Map<String, Object>> products : categories.values()) {
                for (Map<String, Object> product : products) {
                    if (slug.equals(product.get("slug"))) {
                        cartItems.add(product);
                        break;
                    }
                }
            }
        }
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/service/{slug}")
    public String servicePage(@PathVariable String slug, Model model) {
        Map<String, Object> pageData = servicePageData(slug);
        if (pageData == null) {
            return "redirect:/";
        }
        model.addAllAttributes(pageData);
        return "info-page";
    }

    @GetMapping("/brand/{slug}")
    public String brandPage(@PathVariable String slug, Model model) {
        Map<String, Object> pageData = brandPageData(slug);
        if (pageData == null) {
            return "redirect:/";
        }
        model.addAllAttributes(pageData);
        return "info-page";
    }

    @GetMapping("/sale")
    public String salePage(Model model) {
        model.addAttribute("pageTitle", "Sale đặc biệt");
        model.addAttribute("pageDescription", "Khám phá những ưu đãi, sản phẩm giảm giá và gợi ý phong cách nội thất với mức giá hấp dẫn.");
        List<Map<String, Object>> blocks = new ArrayList<>();
        Map<String, Object> block1 = new LinkedHashMap<>();
        block1.put("title", "Ưu đãi chọn lọc");
        block1.put("text", "Các sản phẩm sale được tuyển chọn cẩn thận để vẫn giữ được chất lượng và thiết kế đặc trưng Mộc & Vàng.");
        blocks.add(block1);
        Map<String, Object> block2 = new LinkedHashMap<>();
        block2.put("title", "Giảm giá lên đến 20%");
        block2.put("items", List.of("Sofa, ghế và bàn trà cho phòng khách","Bộ bàn ăn thanh lịch dành cho gia đình","Phụ kiện và decor hoàn thiện không gian"));
        blocks.add(block2);
        model.addAttribute("pageBlocks", blocks);
        return "info-page";
    }

    private Map<String, Object> servicePageData(String slug) {
        return switch (slug) {
            case "tu-van-thiet-ke-mien-phi" -> createPageData(
                    "Tư vấn thiết kế miễn phí",
                    "Nhận tư vấn không gian nội thất phù hợp với nhu cầu và diện tích thực tế của bạn.",
                    List.of(
                            section("Lắng nghe yêu cầu của bạn", "Chúng tôi hiểu rõ thói quen sử dụng và phong cách sống của từng gia đình để đề xuất giải pháp phù hợp."),
                            section("Phối màu & bố cục", "Gợi ý cách phối màu, kết cấu và cách sắp đặt đồ đạc để tối ưu vẻ đẹp và công năng."),
                            section("Tối ưu không gian", "Đề xuất giải pháp cho không gian nhỏ, không gian mở hoặc phòng đa năng với chi tiết thực tế.")
                    )
            );
            case "dat-hang-theo-yeu-cau" -> createPageData(
                    "Đặt hàng theo yêu cầu",
                    "Chúng tôi hỗ trợ đặt làm nội thất theo kích thước, chất liệu và màu sắc riêng của bạn.",
                    List.of(
                            section("Chọn chất liệu thỏa thích", "Lựa chọn gỗ, vải và kim loại phù hợp với phong cách sống và môi trường sử dụng."),
                            section("Thiết kế chuẩn theo kích thước", "Mọi sản phẩm có thể được điều chỉnh theo kích thước riêng để phù hợp chính xác với không gian của bạn."),
                            section("Hoàn thiện với phong cách riêng", "Nhu cầu cá nhân được thể hiện trong từng chi tiết, từ tay vịn đến đường chỉ may.")
                    )
            );
            case "giao-hang-lap-dat" -> createPageData(
                    "Giao hàng & Lắp đặt",
                    "Dịch vụ giao hàng và lắp đặt trọn gói, giúp bạn nhận sản phẩm đúng hạn và sẵn sàng sử dụng ngay.",
                    List.of(
                            section("Giao hàng an toàn", "Sản phẩm được đóng gói kỹ lưỡng, hạn chế va đập trong quá trình vận chuyển."),
                            section("Lắp đặt chuyên nghiệp", "Đội ngũ lắp đặt có kinh nghiệm sẽ hoàn thiện sản phẩm ngay tại nhà bạn."),
                            section("Hướng dẫn bảo quản", "Chúng tôi hướng dẫn cách sử dụng và bảo quản để nội thất giữ được độ bền lâu dài.")
                    )
            );
            case "chinh-sach-bao-hanh" -> createPageData(
                    "Chính sách bảo hành",
                    "Chúng tôi đem lại sự yên tâm với chính sách bảo hành rõ ràng và trách nhiệm sau bán hàng tận tâm.",
                    List.of(
                            section("Bảo hành 12 tháng", "Bảo hành chính hãng cho khung, mối ghép và hoàn thiện bề mặt."),
                            section("Hỗ trợ sửa chữa", "Hỗ trợ sửa chữa trong trường hợp lỗi kỹ thuật hoặc hỏng hóc do sản xuất."),
                            section("Chính sách bảo hành minh bạch", "Quy trình bảo hành rõ ràng, không thêm phí ẩn khi sản phẩm thuộc diện bảo hành.")
                    )
            );
            case "doi-tra" -> createPageData(
                    "Đổi trả",
                    "Dễ dàng đổi trả trong 30 ngày nếu sản phẩm không phù hợp với không gian hoặc kỳ vọng của bạn.",
                    List.of(
                            section("Điều kiện đổi trả", "Sản phẩm còn nguyên vẹn, đầy đủ phụ kiện và chưa qua sử dụng mới có thể đổi trả."),
                            section("Hỗ trợ nhanh chóng", "Chúng tôi hỗ trợ nhận lại hàng và đổi sang sản phẩm khác phù hợp hơn."),
                            section("Trải nghiệm an tâm", "Mua sắm tự tin hơn với chính sách đổi trả rõ ràng và linh hoạt.")
                    )
            );
            case "showroom" -> createPageData(
                    "Showroom",
                    "Khám phá không gian trưng bày Mộc & Vàng, nơi bạn có thể trải nghiệm sản phẩm thực tế và nhận tư vấn trực tiếp.",
                    List.of(
                            section("Không gian trải nghiệm", "Showroom được thiết kế để thể hiện cảm hứng nội thất ấm áp và tinh tế."),
                            section("Tư vấn tại chỗ", "Đội ngũ tư vấn sẽ đồng hành cùng bạn trong buổi gặp để chọn lựa phong cách phù hợp."),
                            section("Đặt lịch trước", "Đặt lịch tham quan showroom để có trải nghiệm riêng tư và tốt nhất.")
                    )
            );
            default -> null;
        };
    }

    private Map<String, Object> brandPageData(String slug) {
        return switch (slug) {
            case "cau-chuyen-cua-chung-toi" -> createPageData(
                    "Câu chuyện của chúng tôi",
                    "Từ cảm hứng thiết kế đến xưởng sản xuất, đây là hành trình tạo nên thương hiệu nội thất Mộc & Vàng.",
                    List.of(
                            section("Bắt đầu từ đam mê", "Chúng tôi bắt đầu với mong muốn tạo ra nội thất vừa đẹp vừa bền, dành cho không gian sống hiện đại.") ,
                            section("Giữ vững giá trị thủ công", "Mỗi chi tiết đều được trau chuốt bởi những người thợ tài hoa với niềm tự hào nghề nghiệp."),
                            section("Phục vụ không gian Việt", "Thiết kế chú trọng tính tiện nghi, thoải mái và phù hợp với thói quen sống của gia đình Việt.")
                    )
            );
            case "xuong-san-xuat" -> createPageData(
                    "Xưởng sản xuất",
                    "Xưởng của chúng tôi với quy trình sản xuất khép kín giúp kiểm soát chất lượng từ nguyên liệu đến thành phẩm.",
                    List.of(
                            section("Đội ngũ thợ lành nghề", "Các thợ mỹ nghệ có kinh nghiệm nhiều năm, đảm bảo từng đường ghép và lớp hoàn thiện đều chính xác."),
                            section("Máy móc hỗ trợ chính xác", "Chúng tôi kết hợp máy móc hiện đại với tay nghề thủ công để đạt độ hoàn thiện cao nhất."),
                            section("Kiểm soát chất lượng", "Mỗi sản phẩm được kiểm tra kỹ lưỡng trước khi rời xưởng để đảm bảo độ bền và thẩm mỹ.")
                    )
            );
            case "cam-ket-ben-vung" -> createPageData(
                    "Cam kết bền vững",
                    "Chúng tôi xây dựng thương hiệu với sự tôn trọng môi trường và trách nhiệm với nguồn nguyên liệu.",
                    List.of(
                            section("Nguồn gỗ có chứng nhận", "Ưu tiên gỗ có nguồn gốc rõ ràng và được quản lý bền vững."),
                            section("Thiết kế giảm lãng phí", "Thiết kế thông minh giúp tận dụng vật liệu và giảm phế phẩm trong sản xuất."),
                            section("Hỗ trợ lâu dài", "Chúng tôi tư vấn bảo quản và sửa chữa để nội thất sử dụng được lâu dài.")
                    )
            );
            case "nhat-ky-thiet-ke" -> createPageData(
                    "Nhật ký thiết kế",
                    "Cập nhật những câu chuyện, xu hướng và dự án nội thất mới nhất từ Mộc & Vàng.",
                    List.of(
                            section("Dự án tiêu biểu", "Những căn hộ, biệt thự và không gian làm việc được chúng tôi thiết kế và thi công trọn gói."),
                            section("Xu hướng hiện đại", "Những gam màu, chất liệu và phong cách đang được ưa chuộng trong thiết kế nội thất.") ,
                            section("Câu chuyện khách hàng", "Những bài học và trải nghiệm từ khách hàng thực tế giúp chúng tôi hoàn thiện dịch vụ.")
                    )
            );
            case "tuyen-dung" -> createPageData(
                    "Tuyển dụng",
                    "Cùng Mộc & Vàng tạo ra không gian nội thất có giá trị và bền vững. Chúng tôi tìm kiếm những nhân sự yêu nghề và nhiệt huyết.",
                    List.of(
                            section("Vị trí cần tuyển", "Thợ mộc, nhân viên tư vấn nội thất, thiết kế và nhân viên chăm sóc khách hàng."),
                            section("Môi trường làm việc", "Xưởng thân thiện, sáng tạo và tôn trọng tay nghề từng cá nhân."),
                            section("Quyền lợi", "Đào tạo, lương cạnh tranh và cơ hội phát triển nghề nghiệp lâu dài.")
                    )
            );
            case "lien-he" -> createPageData(
                    "Liên hệ",
                    "Gặp gỡ Mộc & Vàng để nhận tư vấn nhanh chóng và thông tin chi tiết về sản phẩm.",
                    List.of(
                            section("Hỗ trợ qua email", "Gửi câu hỏi về đặt hàng và thiết kế đến email của chúng tôi."),
                            section("Tư vấn qua điện thoại", "Nhận tư vấn trực tiếp về kích thước, chất liệu và cách lên ý tưởng."),
                            section("Đặt lịch tham quan", "Hẹn lịch đến showroom để trải nghiệm sản phẩm thực tế.")
                    )
            );
            default -> null;
        };
    }

    private Map<String, Object> createPageData(String title, String description, List<Map<String, Object>> blocks) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("pageTitle", title);
        data.put("pageDescription", description);
        data.put("pageBlocks", blocks);
        return data;
    }

    private Map<String, Object> section(String title, String text) {
        Map<String, Object> section = new LinkedHashMap<>();
        section.put("title", title);
        section.put("text", text);
        return section;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}