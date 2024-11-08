
SELECT COUNT(DISTINCT s.snum) AS fm_students_count
FROM students s
JOIN (
    SELECT snum, name, level FROM major
    UNION
    SELECT snum, name, level FROM minor
) AS combined ON s.snum = combined.snum
JOIN degrees d ON combined.name = d.name AND combined.level = d.level
JOIN departments dept ON d.department_code = dept.code
WHERE s.gender = 'F'
AND dept.name = 'Software Engineering';