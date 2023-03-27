# FPluginLoader
FPluginLoader é uma biblioteca de código aberto que tem como objetivo auxiliar no desenvolvimento de plugins para Bukkit/Spigot/Bungee, tornando o processo mais fácil, rápido e eficiente. Além disso, ela também serve como uma ótima ferramenta de estudo para quem deseja aprender mais sobre o desenvolvimento de plugins para Minecraft.

A biblioteca oferece diversos recursos e funcionalidades que facilitam a criação e o gerenciamento de plugins, permitindo que os desenvolvedores foquem em implementar as funcionalidades específicas de seus plugins. Porém, é importante lembrar de adaptar o código conforme suas necessidades, especialmente o sistema de licença, que deve ser ajustado para a URL do seu site. Depois de feita a adaptação, aproveite os recursos oferecidos pela biblioteca para otimizar e aprimorar seus plugins!

### Em desenvolvimento
Sinta-se a vontade em colaborar com o projeto, seja criando ou modificando os sistemas.

# Bukkit
Para fazer um plugin utilizando o BukkitPlugin ao em vez do JavaPlugin, utiliza-se o padrão a baixo.
```java
import com.github.fleivinho.fpluginloader.platform.bukkit.BukkitPlugin;
import com.github.fleivinho.fpluginloader.platform.bukkit.LoaderInfo;
import lombok.Getter;

public class PluginMain extends BukkitPlugin {

    @Getter
    private BukkitPlugin instance;

    public PluginMain() {
        super(new LoaderInfo("FPluginLoader", false));
    }

    @Override
    public void load() {
        instance = this;
    }
    
    @Override
    public void enable() {
        // Carregará todas as classes Commands e Listener dentro da package:
        loadPackage("com.github.fleivinho.fpluginloader.example.bukkit.commands");
        
        instance.getLogger().info("Plugin inicializado com sucesso!");
    }

    @Override
    public void disable() {
        instance.getLogger().info("Plugin desligado com sucesso!");
    }
}

```
