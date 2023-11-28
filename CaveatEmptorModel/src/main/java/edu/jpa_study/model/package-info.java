// в книге описан старый вариант через style,
// там же приведены классы, которые реализуют эти стратегии
// (стр. 147 (101 в рус.) по файлу )
// Для hibernate в persistence.xml необходимо указать пакет,
// где находится данный файл в <class>edu.jpa_study.model</class>
@GenericGenerator(
        // Имя генератора, его можно указывать в @GeneratedValue(generator = "имя_генератора")
        name = "ID_GENERATOR",
        // Используем последовательности БД, если поддерживаются, иначе
        // создаётся таблица, иммитирующая такую последовательность.
        // Такая стратегия вызывает последовательность ПЕРЕД каждым INSERT
        type = SequenceStyleGenerator.class, // strategy = "enhanced-sequence"
        parameters = {
                @Parameter(
                        // Имя последовательности (в БД, можно поглядеть в pgAdmin)
                        name = "sequence_name",
                        value = "CAVEAT_EMPTOR_SEQUENCE"
                ),
                @Parameter(
                        // Стартовое значение идентификатора
                        name = "initial_value",
                        value = "1000"
                ),
                @Parameter(
                        name = "increment_size",
                        value = "1"
                )
        }
)
package edu.jpa_study.model;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
