package online.palworldkorea.palworldkorea_online.admin.common.repository;

import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.entity.CommonAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonAdminRepository extends JpaRepository<CommonAdmin, Long> {
    Optional<CommonAdmin> findByAdminInventoryType(AdminInventoryType adminInventoryType);
}
