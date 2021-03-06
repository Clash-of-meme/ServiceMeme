/*
 * Service gestion meme
 * Service permettant de créer des memes et de les récupérer
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Objet JSON Meme Pattern
 */
@ApiModel(description = "Objet JSON Meme Pattern")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MemePattern   {
  @JsonProperty("id_imgflip")
  private String idImgflip = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("text_top")
  private String textTop = null;

  @JsonProperty("text_bottom")
  private String textBottom = null;

  @JsonProperty("token_user")
  private String tokenUSer = null;

  public MemePattern idImgflip(String idImgflip) {
    this.idImgflip = idImgflip;
    return this;
  }

   /**
   * Get idImgflip
   * @return idImgflip
  **/
  @ApiModelProperty(value = "")
  public String getIdImgflip() {
    return idImgflip;
  }

  public void setIdImgflip(String idImgflip) {
    this.idImgflip = idImgflip;
  }

  public MemePattern name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MemePattern textTop(String textTop) {
    this.textTop = textTop;
    return this;
  }

   /**
   * Get textTop
   * @return textTop
  **/
  @ApiModelProperty(value = "")
  public String getTextTop() {
    return textTop;
  }

  public void setTextTop(String textTop) {
    this.textTop = textTop;
  }

  public MemePattern textBottom(String textBottom) {
    this.textBottom = textBottom;
    return this;
  }

   /**
   * Get textBottom
   * @return textBottom
  **/
  @ApiModelProperty(value = "")
  public String getTextBottom() {
    return textBottom;
  }

  public void setTextBottom(String textBottom) {
    this.textBottom = textBottom;
  }

    /**
     * Get token user
     * @return textTop
     **/
    @ApiModelProperty(value = "")
    public String getTokenUSer() {
        return tokenUSer;
    }

    public void setTokenUser(String token) {
        this.tokenUSer = token;
    }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemePattern memePattern = (MemePattern) o;
    return Objects.equals(this.idImgflip, memePattern.idImgflip) &&
        Objects.equals(this.name, memePattern.name) &&
        Objects.equals(this.textTop, memePattern.textTop) &&
        Objects.equals(this.textBottom, memePattern.textBottom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idImgflip, name, textTop, textBottom);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    
    sb.append('"').append("id_imgflip").append('"').append(": ").append('"').append(toIndentedString(idImgflip)).append('"').append(',').append("\n");
    sb.append('"').append("name").append('"').append(": ").append('"').append(toIndentedString(name)).append('"').append(',').append("\n");
    sb.append('"').append("tex_top").append('"').append(": ").append('"').append(toIndentedString(textTop)).append('"').append(',').append("\n");
    sb.append('"').append("text_bottom").append('"').append(": ").append('"').append(toIndentedString(textBottom)).append('"').append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

